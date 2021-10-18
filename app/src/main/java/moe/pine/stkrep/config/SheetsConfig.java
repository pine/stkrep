package moe.pine.stkrep.config;

import moe.pine.stkrep.properties.SheetsProperties;
import moe.pine.stkrep.sheets.forecast.ForecastSheets;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;

@Configuration
@EnableConfigurationProperties(SheetsProperties.class)
public class SheetsConfig {
    @Bean
    public ForecastSheets forecastSheets(
            ResourceLoader resourceLoader,
            SheetsProperties sheetsProperties
    ) throws IOException {
        final Resource resource = resourceLoader.getResource(sheetsProperties.credentialsPath());

        return ForecastSheets.create(
                sheetsProperties.applicationName(),
                sheetsProperties.forecastSheets(),
                resource.getInputStream()
        );
    }
}
