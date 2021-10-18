package moe.pine.stkrep.properties;

import moe.pine.stkrep.sheets.ForecastSheetsProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Properties for Google Sheets
 *
 * @see <a href="https://developers.google.com/sheets/api/guides/concepts">Google Sheets API Overview | Google Developers</a>
 */
@Validated
@ConstructorBinding
@ConfigurationProperties("sheets")
public record SheetsProperties(
        @NotBlank String credentialsPath,
        @NotBlank String applicationName,
        @NotNull ForecastSheetsProperties forecastSheets
) {
}