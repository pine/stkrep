package moe.pine.stkrep.sheets;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ClearValuesRequest;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.common.annotations.VisibleForTesting;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moe.pine.stkrep.sheets.exceptions.RetryableException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @see <a href="https://developers.google.com/sheets/api/quickstart/java"><title>Java Quickstart | Sheets API | Google Developers</a>
 */
@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class ForecastSheets {
    private static final Pattern CODE_PATTERN = Pattern.compile("\\p{Digit}+");

    private final Sheets sheets;
    private final ForecastSheetsProperties forecastSheetsProperties;

    public static ForecastSheets create(
            String applicationName,
            ForecastSheetsProperties forecastSheetsProperties,
            InputStream credentialsStream
    ) {
        final Sheets sheets = SheetsFactory.create(applicationName, credentialsStream);
        return new ForecastSheets(sheets, forecastSheetsProperties);
    }

    /**
     * Get stocks codes
     *
     * @see <a href="https://developers.google.com/sheets/api/reference/rest/v4/spreadsheets.values/get">Method: spreadsheets.values.get | Sheets API | Google Developers</a>
     */
    public List<String> getCodes() {
        return getCodesWithNoRetry(); // TODO: retry
    }

    @VisibleForTesting
    List<String> getCodesWithNoRetry() {
        final String spreadsheetId = forecastSheetsProperties.spreadsheetId();
        final String codesRange = forecastSheetsProperties.codesRange();

        log.debug("Start getting the values. [spreadsheet-id={}, codes-range={}]", spreadsheetId, codesRange);

        final ValueRange valueRange;
        try {
            valueRange = sheets.spreadsheets()
                    .values()
                    .get(spreadsheetId, codesRange)
                    .execute();
        } catch (IOException e) {
            throw new RetryableException(
                    String.format("Unable to get values from spreadsheets. [spreadsheet-id=%s, codes-range=%s]",
                            spreadsheetId, codesRange), e);
        }

        final List<List<Object>> values = valueRange.getValues();
        log.debug("Finished getting the values. [spreadsheet-id={}, codes-range={}, values={}]",
                spreadsheetId, codesRange, values);

        return values.stream()
                .flatMap(Collection::stream)
                .map(String::valueOf)
                .filter(v -> CODE_PATTERN.matcher(v).matches())
                .toList();
    }

    public void putResult() {
        clearWithNoRetry(); // TODO: retry
    }

    void clearWithNoRetry() {
        final String spreadsheetId = forecastSheetsProperties.spreadsheetId();
        try {
            sheets.spreadsheets()
                    .values()
                    .clear(spreadsheetId, "結果", new ClearValuesRequest())
                    .execute();
        } catch (IOException e) {
            throw new RetryableException("", e);
        }
    }
}
