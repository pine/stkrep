package moe.pine.stkrep.kabuyoho.internal;

import org.springframework.web.reactive.function.client.WebClient;

public class Browser {
    private static final String ENDPOINT = "https://kabuyoho.ifis.co.jp/index.php?action=tp1&sa=report_top&bcode=";
    private static final WebClient WEB_CLIENT = WebClient.create();

    public BrowsingResults browse(Integer code) {
        final String uri = ENDPOINT + code;
        final String body =
                WEB_CLIENT.get()
                        .uri(uri)
                        .retrieve()
                        .bodyToMono(String.class)
                        .blockOptional()
                        .orElse(""); // TODO

        return new BrowsingResults(code, uri, body);
    }
}
