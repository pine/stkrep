package moe.pine.stkrep.config;

import moe.pine.stkrep.ua.CommonUserAgents;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;

@Configuration
public class UserAgentConfig {
    @Bean
    public CommonUserAgents commonUserAgents(
            ResourceLoader resourceLoader
    ) throws IOException {
        final Resource resource = resourceLoader.getResource("classpath:ua.json");
        return CommonUserAgents.ofStream(resource.getInputStream());
    }
}
