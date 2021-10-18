package moe.pine.stkrep.sheets;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * @param codesRange Range of stocks codes
 */
@Validated
public record ForecastSheetsProperties(
        @NotBlank String spreadsheetId,
        @NotBlank String codesRange
//        @NotBlank String resultSheetName,
//        @NotNull Integer resultOffsetY
) {
}
