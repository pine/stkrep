package moe.pine.stkrep.kabuyoho.browser;

import com.google.common.annotations.VisibleForTesting;
import moe.pine.reactor.interruptible.MonoUtils;
import moe.pine.stkrep.kabuyoho.exceptions.NonRetryableException;
import moe.pine.stkrep.kabuyoho.exceptions.RetryableException;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

public class Browser {
    private static final WebClient WEB_CLIENT = WebClient.create(); // TODO

    private static final String BASE_URL = "https://kabuyoho.jp/reportTop?bcode=";
    private static final Duration BLOCK_TIMEOUT = Duration.ofSeconds(60L);

    public BrowsingResults browse(int code) {
        // TODO: retry
        return browseWithNoRetry(code);
    }

    @VisibleForTesting
    BrowsingResults browseWithNoRetry(int code) {
        final String uri = BASE_URL + code;
        final String body;
        try {
            body = MonoUtils.blockOptional(
                            WEB_CLIENT.get().uri(uri).retrieve().bodyToMono(String.class),
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
