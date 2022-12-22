package moe.pine.stkrep.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Properties for Google Sheets
 */
@Validated
@ConstructorBinding
@ConfigurationProperties("sheets")
public record SheetsProperties(
        @NotBlank String credentials,
        @NotBlank String applicationName,
        @NotNull SheetsProperties.ForecastSheets forecastSheets
) {
    @Validated
    @ConstructorBinding
    public record ForecastSheets(
            @NotBlank String spreadsheetId,
            @NotBlank String codesRange,
            @NotBlank String resultSheetName,
            @NotNull Integer resultOffsetY
    ) {
    }
}
