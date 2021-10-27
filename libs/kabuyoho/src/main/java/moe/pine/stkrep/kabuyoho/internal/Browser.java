package moe.pine.stkrep.kabuyoho.internal;

import moe.pine.reactor.interruptible.MonoUtils;
import org.springframework.web.reactive.function.client.WebClient;

public class Browser {
    private static final String ENDPOINT = "https://kabuyoho.ifis.co.jp/index.php?action=tp1&sa=report_top&bcode=";
    private static final WebClient WEB_CLIENT = WebClient.create();

    public BrowsingResults browse(Integer code) {
        final String uri = ENDPOINT + code;
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
