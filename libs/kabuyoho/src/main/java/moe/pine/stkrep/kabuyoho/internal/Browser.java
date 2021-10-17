package moe.pine.stkrep.kabuyoho.internal;

import org.springframework.web.reactive.function.client.WebClient;

public class Browser {
    private static final String ENDPOINT = "https://kabuyoho.ifis.co.jp/index.php?action=tp1&sa=report_top&bcode={code}";

    private final WebClient webClient = WebClient.create();

    public BrowsingResults browse(String code) {
        final String body =
                webClient.get()
                        .uri(ENDPOINT, code)
                        .retrieve()
                        .bodyToMono(String.class)
                        .blockOptional()
                        .orElse(""); // TODO

        return new BrowsingResults(body);
    }
}
