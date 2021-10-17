package moe.pine.stkrep.kabuyoho;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
public class Kabuyoho {
    private final WebClient webClient = WebClient.create();


    public void find(String code) {
        final String endpoint = "https://kabuyoho.ifis.co.jp/index.php?action=tp1&sa=report_ts&bcode=" + code;
        final String body =
                webClient.get()
                        .uri(endpoint)
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();
        log.debug("{}", body);
    }
}
