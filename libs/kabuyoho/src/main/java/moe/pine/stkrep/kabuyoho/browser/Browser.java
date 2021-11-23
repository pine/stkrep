package moe.pine.stkrep.kabuyoho.browser;

import com.google.common.annotations.VisibleForTesting;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moe.pine.reactor.interruptible.MonoUtils;
import moe.pine.stkrep.kabuyoho.UserAgentSupplier;
import moe.pine.stkrep.kabuyoho.exception.NonRetryableException;
import moe.pine.stkrep.kabuyoho.exception.RetryableException;
import moe.pine.stkrep.retry.RetryTemplateFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class Browser {
    private static final String BASE_URL = "https://kabuyoho.jp/reportTop?bcode=";
    private static final Duration BLOCK_TIMEOUT = Duration.ofSeconds(60L);

    private final WebClient webClient;
    private final RetryTemplate retryTemplate;
    private final UserAgentSupplier userAgentSupplier;

    public Browser(
            UserAgentSupplier userAgentSupplier
    ) {
        this(WebClient.create(),
                RetryTemplateFactory.create(10, 1000, 2.0, RetryableException.class),
                userAgentSupplier);
    }

    public BrowsingResults browse(int code) {
        return retryTemplate.execute(ctx -> {
            try {
                return browseWithNoRetry(code);
            } catch (RetryableException e) {
                final String msg = String.format("Failed to execute. [retry-count=%d]", ctx.getRetryCount());
                log.warn(msg, e);
                throw new RetryableException(msg, e);
            } catch (RuntimeException e) {
                final String msg = String.format("Gave up on the execution. [retry-count=%d]", ctx.getRetryCount());
                log.error(msg, e);
                throw new NonRetryableException(msg, e);
            }
        });
    }

    @VisibleForTesting
    BrowsingResults browseWithNoRetry(int code) {
        final String uri = BASE_URL + code;
        final String body;
        try {
            body = MonoUtils.blockOptional(
                            webClient.get()
                                    .uri(uri)
                                    .header(HttpHeaders.USER_AGENT, userAgentSupplier.get())
                                    .retrieve()
                                    .bodyToMono(String.class),
                            BLOCK_TIMEOUT)
                    .orElseThrow(() -> new IllegalStateException("Mono.empty returned."));
        } catch (RuntimeException e) {
            throw new RetryableException(
                    String.format("Unable to get response body. [code=%d, uri=%s]", code, uri), e);
        } catch (InterruptedException e) {
            throw new NonRetryableException(
                    String.format("Unable to get response body. [code=%d, uri=%s]", code, uri), e);
        }

        return new BrowsingResults(code, uri, body);
    }
}
