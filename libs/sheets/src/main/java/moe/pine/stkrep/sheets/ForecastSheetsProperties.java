package moe.pine.stkrep.sheets;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;


/**
 * @param codesRange Range of stocks codes
 */
@Validated
public record ForecastSheetsProperties(
        @NotBlank String spreadsheetId,
        @NotBlank String codesRange
) {
}
