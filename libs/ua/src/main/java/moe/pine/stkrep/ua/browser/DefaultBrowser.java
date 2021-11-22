package moe.pine.stkrep.ua.browser;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class DefaultBrowser implements Browser {
    private static final String URI = "https://techblog.willshouse.com/2012/01/03/most-common-user-agents/";
    private static final Duration BLOCK_TIMEOUT = Duration.ofSeconds(60L);

    static final DefaultBrowser INSTANCE = new DefaultBrowser(WebClient.create());

    private final WebClient webClient;

    @Override
    public BrowsingResults browse() {
        throw new RuntimeException("not implemented");
    }
}
