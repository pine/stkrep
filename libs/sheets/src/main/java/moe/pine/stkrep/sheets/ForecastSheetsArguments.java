package moe.pine.stkrep.sheets;

public record ForecastSheetsArguments(
        String applicationName,
        String spreadsheetId,
        String codesRange,
        String resultSheetTitle,
        int resultOffsetY
) {
}
