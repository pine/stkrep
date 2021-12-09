package moe.pine.stkrep.sheets;

public record ForecastSheetsDetails(
        String applicationName,
        String spreadsheetId,
        String codesRange,
        String resultSheetTitle,
        int resultOffsetY
) {
}
