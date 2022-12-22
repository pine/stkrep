package moe.pine.stkrep.config;

import moe.pine.stkrep.properties.SheetsProperties;
import moe.pine.stkrep.sheets.ForecastSheets;
import moe.pine.stkrep.sheets.ForecastSheetsDetails;
import org.apache.commons.io.IOUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Configuration
@EnableConfigurationProperties(SheetsProperties.class)
public class SheetsConfig {
    @Bean
    public ForecastSheets forecastSheets(
            ResourceLoader resourceLoader,
            SheetsProperties sheetsProperties
    ) throws IOException {
        final var credentials = sheetsProperties.credentials();
        final ForecastSheetsDetails details =
                new ForecastSheetsDetails(
                        sheetsProperties.applicationName(),
                        sheetsProperties.forecastSheets().spreadsheetId(),
                        sheetsProperties.forecastSheets().codesRange(),
                        sheetsProperties.forecastSheets().resultSheetName(),
                        sheetsProperties.forecastSheets().resultOffsetY()
                );

        try (InputStream stream = IOUtils.toInputStream(credentials, StandardCharsets.UTF_8)) {
            return ForecastSheets.create(stream, details);
        }
    }
}
