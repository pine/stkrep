package moe.pine.stkrep.kabuyoho.internal;

import moe.pine.reactor.interruptible.MonoUtils;
import org.springframework.web.reactive.function.client.WebClient;

public class Browser {
    private static final String BASE_URL = "https://kabuyoho.jp/reportTop?bcode=";
    private static final WebClient WEB_CLIENT = WebClient.create();

    public BrowsingResults browse(Integer code) {
        final String uri = BASE_URL + code;
        final String body;

        try {
            body = MonoUtils.blockOptional(
                            WEB_CLIENT.get()
                                    .uri(uri)
                                    .retrieve()
                                    .bodyToMono(String.class))
                    .orElseThrow(() -> new IllegalStateException("Empty body received."));
        } catch (RuntimeException e) {
            throw new RetryableException(e);
        } catch (InterruptedException e) {
            throw new NonRetryableException(e);
        }

        return new BrowsingResults(code, uri, body);
    }
}
