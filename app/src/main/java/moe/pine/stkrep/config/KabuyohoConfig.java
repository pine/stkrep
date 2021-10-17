package moe.pine.stkrep.config;

import moe.pine.stkrep.kabuyoho.Kabuyoho;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KabuyohoConfig {
    @Bean
    public Kabuyoho kabuyoho() {
        return new Kabuyoho();
    }
}
