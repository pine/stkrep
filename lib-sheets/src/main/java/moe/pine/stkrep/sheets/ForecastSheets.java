package moe.pine.stkrep.sheets;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetRequest;
import com.google.api.services.sheets.v4.model.ClearValuesRequest;
import com.google.api.services.sheets.v4.model.ClearValuesResponse;
import com.google.api.services.sheets.v4.model.GridCoordinate;
import com.google.api.services.sheets.v4.model.Request;
import com.google.api.services.sheets.v4.model.RowData;
import com.google.api.services.sheets.v4.model.Sheet;
import com.google.api.services.sheets.v4.model.SheetProperties;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.UpdateCellsRequest;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.common.annotations.VisibleForTesting;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import moe.pine.stkrep.report.ForecastReport;
import moe.pine.stkrep.sheets.column.Column;
import moe.pine.stkrep.sheets.column.ForecastColumn;
import moe.pine.stkrep.sheets.exception.RetryableException;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;


/**
 * Spreadsheets for forecast
 *
 * @see <a href="https://note.com/npaka/n/nd522e980d995">GCP 入門 (18) - Google Sheets API</a>
 * @see <a href="https://developers.google.com/sheets/api/guides/concepts">Google Sheets API Overview | Google Developers</a>
 * @see <a href="https://developers.google.com/sheets/api/quickstart/java">Java Quickstart | Sheets API | Google Developers</a>
 */
@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class ForecastSheets {
    private final Sheets sheets;
    private final ForecastSheetsDetails details;

    public static ForecastSheets create(
            InputStream credentialsStream,
            ForecastSheetsDetails details
    ) {
        final Sheets sheets = SheetsFactory.create(details.applicationName(), credentialsStream);
        return new ForecastSheets(sheets, details);
    }

    /**
     * Get stocks codes
     *
     * @see <a href="https://developers.google.com/sheets/api/reference/rest/v4/spreadsheets.values/get">Method: spreadsheets.values.get | Sheets API | Google Developers</a>
     */
    public List<Integer> getCodes() {
        return getCodesWithNoRetry(); // TODO: retry
    }

    @VisibleForTesting
    List<Integer> getCodesWithNoRetry() {
        log.debug("Start getting the values. [spreadsheet-id={}, codes-range={}]",
                details.spreadsheetId(), details.codesRange());

        final ValueRange valueRange;
        try {
            valueRange = sheets.spreadsheets()
                    .values()
                    .get(details.spreadsheetId(), details.codesRange())
                    .execute();
        } catch (IOException e) {
            throw new RetryableException(
                    String.format("Unable to get values from spreadsheets. [spreadsheet-id=%s, codes-range=%s]",
                            details.spreadsheetId(), details.codesRange()), e);
        }

        final List<List<Object>> values = valueRange.getValues();
        log.debug("Finished getting the values. [spreadsheet-id={}, codes-range={}, values={}]",
                details.spreadsheetId(), details.codesRange(), values);


        return values.stream()
                .flatMap(Collection::stream)
                .map(String::valueOf)
                .filter(NumberUtils::isDigits)
                .map(NumberUtils::toInt)
                .toList();
    }

    public void updateResult(List<ForecastReport> reports) {
        final int resultSheetId = getResultSheetId();

        clearWithNoRetry();
        updateResultWithNoRetry(resultSheetId, reports);
    }

    @VisibleForTesting
    int getResultSheetId() {
        log.debug("Start getting the result sheet ID. [spreadsheet-id={}, sheet-title={}]",
                details.spreadsheetId(), details.resultSheetTitle());

        final Spreadsheet spreadsheet;
        try {
            spreadsheet =
                    sheets.spreadsheets()
                            .get(details.spreadsheetId())
                            .setIncludeGridData(false)
                            .execute();
        } catch (IOException e) {
            throw new RetryableException(
                    String.format("Unable to get the spreadsheet. [spreadsheet-id=%s]",
                            details.spreadsheetId()), e);
        }

        final int resultSheetId = spreadsheet.getSheets()
                .stream()
                .map(Sheet::getProperties)
                .filter(v -> v.getTitle().equals(details.resultSheetTitle()))
                .map(SheetProperties::getSheetId)
                .findFirst()
                .orElseThrow(() -> new RetryableException("Unable to find the result sheet ID."));

        log.debug("Finished getting the result sheet ID. [spreadsheet-id={},  sheet-title={}, result-sheet-id={}]",
                details.spreadsheetId(), details.resultSheetTitle(), resultSheetId);

        return resultSheetId;
    }

    @VisibleForTesting
    void clearWithNoRetry() {
        final String range =
                String.format("%s!R%dC1:C%d", details.resultSheetTitle(),
                        details.resultOffsetY() + 1, Column.MAX_NUMBER_OF_COLUMNS);

        log.debug("Start clearing values. [spreadsheet-id={}], range={}]", details.spreadsheetId(), range);

        final ClearValuesResponse clearValuesResponse;
        try {
            clearValuesResponse =
                    sheets.spreadsheets()
                            .values()
                            .clear(details.spreadsheetId(), range, new ClearValuesRequest())
                            .execute();
        } catch (IOException e) {
            throw new RetryableException(
                    String.format("Unable to clear values. [spreadsheet-id=%s, range=%s]",
                            details.spreadsheetId(), range), e);
        }

        log.debug("Finished clearing values. [spreadsheet-id={}], range={}, response={}]",
                details.spreadsheetId(), range, clearValuesResponse);
    }

    @VisibleForTesting
    void updateResultWithNoRetry(
            int resultSheetId,
            List<ForecastReport> reports
    ) {
        log.debug("Start updating the result in the spreadsheet. " +
                        "[spreadsheet-id={}, result-sheet-id={}, result-offset-y={}]",
                details.spreadsheetId(), resultSheetId, details.resultOffsetY());

        final List<RowData> rows = Column.collect(reports, ForecastColumn.class);
        final UpdateCellsRequest updateCellsRequest = new UpdateCellsRequest()
                .setFields("*")
                .setRows(rows)
                .setStart(new GridCoordinate()
                        .setSheetId(resultSheetId)
                        .setRowIndex(details.resultOffsetY())
                        .setColumnIndex(0));

        final Request request = new Request().setUpdateCells(updateCellsRequest);
        final BatchUpdateSpreadsheetRequest batchUpdateSpreadsheetRequest =
                new BatchUpdateSpreadsheetRequest().setRequests(List.of(request));

        try {
            sheets.spreadsheets()
                    .batchUpdate(details.spreadsheetId(), batchUpdateSpreadsheetRequest)
                    .execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
