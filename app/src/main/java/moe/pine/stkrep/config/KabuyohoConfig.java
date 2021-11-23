package moe.pine.stkrep.config;

import lombok.extern.slf4j.Slf4j;
import moe.pine.stkrep.kabuyoho.Kabuyoho;
import moe.pine.stkrep.ua.CommonUserAgents;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class KabuyohoConfig {
    @Bean
    public Kabuyoho kabuyoho(
            CommonUserAgents commonUserAgents
    ) {
        return new Kabuyoho(commonUserAgents::sample);
    }
}
