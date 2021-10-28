package moe.pine.stkrep.kabuyoho.browser;

import com.google.common.annotations.VisibleForTesting;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moe.pine.reactor.interruptible.MonoUtils;
import moe.pine.stkrep.kabuyoho.exceptions.NonRetryableException;
import moe.pine.stkrep.kabuyoho.exceptions.RetryableException;
import moe.pine.stkrep.retry.RetryTemplateFactory;
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

    public Browser() {
        this(WebClient.create(),
                RetryTemplateFactory.create(5, 1000, 5.0, RetryableException.class));
    }

    public BrowsingResults browse(int code) {
        return retryTemplate.execute(ctx -> {
            try {
                return browseWithNoRetry(code);
            } catch (RuntimeException e) {
                log.warn("Failed to execute. [retry-count={}]", ctx.getRetryCount(), e);
                throw e;
            }
        });
    }

    @VisibleForTesting
    BrowsingResults browseWithNoRetry(int code) {
        final String uri = BASE_URL + code;
        final String body;
        try {
            body = MonoUtils.blockOptional(
                            webClient.get().uri(uri).retrieve().bodyToMono(String.class),
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
