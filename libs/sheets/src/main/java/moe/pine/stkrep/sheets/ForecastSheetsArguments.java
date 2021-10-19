package moe.pine.stkrep.sheets;

public record ForecastSheetsArguments(
        String applicationName,
        String spreadsheetId,
        String codesRange,
        String resultSheetName,
        int resultOffsetY
) {
}
