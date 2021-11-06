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
import moe.pine.stkrep.report.Forecast;
import moe.pine.stkrep.sheets.internal.Columns;
import moe.pine.stkrep.sheets.internal.RetryableException;
import moe.pine.stkrep.sheets.internal.SheetsFactory;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;


/**
 * @see <a href="https://developers.google.com/sheets/api/quickstart/java">Java Quickstart | Sheets API | Google Developers</a>
 */
@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class ForecastSheets {
    private final Sheets sheets;
    private final ForecastSheetsArguments args;

    public static ForecastSheets create(
            InputStream credentialsStream,
            ForecastSheetsArguments args
    ) {
        final Sheets sheets = SheetsFactory.create(args.applicationName(), credentialsStream);
        return new ForecastSheets(sheets, args);
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
                args.spreadsheetId(), args.codesRange());

        final ValueRange valueRange;
        try {
            valueRange = sheets.spreadsheets()
                    .values()
                    .get(args.spreadsheetId(), args.codesRange())
                    .execute();
        } catch (IOException e) {
            throw new RetryableException(
                    String.format("Unable to get values from spreadsheets. [spreadsheet-id=%s, codes-range=%s]",
                            args.spreadsheetId(), args.codesRange()), e);
        }

        final List<List<Object>> values = valueRange.getValues();
        log.debug("Finished getting the values. [spreadsheet-id={}, codes-range={}, values={}]",
                args.spreadsheetId(), args.codesRange(), values);


        return values.stream()
                .flatMap(Collection::stream)
                .map(String::valueOf)
                .filter(NumberUtils::isDigits)
                .map(NumberUtils::toInt)
                .toList();
    }

    public void updateResult(List<Forecast> forecasts) {
        final int resultSheetId = getResultSheetId();

        clearWithNoRetry();
        updateResultWithNoRetry(resultSheetId, forecasts);
    }

    @VisibleForTesting
    int getResultSheetId() {
        log.debug("Start getting the result sheet ID. [spreadsheet-id={}, sheet-title={}]",
                args.spreadsheetId(), args.resultSheetTitle());

        final Spreadsheet spreadsheet;
        try {
            spreadsheet =
                    sheets.spreadsheets()
                            .get(args.spreadsheetId())
                            .setIncludeGridData(false)
                            .execute();
        } catch (IOException e) {
            throw new RetryableException(
                    String.format("Unable to get the spreadsheet. [spreadsheet-id=%s]",
                            args.spreadsheetId()), e);
        }

        final int resultSheetId = spreadsheet.getSheets()
                .stream()
                .map(Sheet::getProperties)
                .filter(v -> v.getTitle().equals(args.resultSheetTitle()))
                .map(SheetProperties::getSheetId)
                .findFirst()
                .orElseThrow(() -> new RetryableException("Unable to find the result sheet ID."));

        log.debug("Finished getting the result sheet ID. [spreadsheet-id={},  sheet-title={}, result-sheet-id={}]",
                args.spreadsheetId(), args.resultSheetTitle(), resultSheetId);

        return resultSheetId;
    }

    @VisibleForTesting
    void clearWithNoRetry() {
        final String range =
                String.format("%s!R%dC1:C%d", args.resultSheetTitle(),
                        args.resultOffsetY() + 1, Columns.MAX_NUMBER_OF_COLUMNS);

        log.debug("Start clearing values. [spreadsheet-id={}], range={}]", args.spreadsheetId(), range);

        final ClearValuesResponse clearValuesResponse;
        try {
            clearValuesResponse =
                    sheets.spreadsheets()
                            .values()
                            .clear(args.spreadsheetId(), range, new ClearValuesRequest())
                            .execute();
        } catch (IOException e) {
            throw new RetryableException(
                    String.format("Unable to clear values. [spreadsheet-id=%s, range=%s]",
                            args.spreadsheetId(), range), e);
        }

        log.debug("Finished clearing values. [spreadsheet-id={}], range={}, response={}]",
                args.spreadsheetId(), range, clearValuesResponse);
    }

    @VisibleForTesting
    void updateResultWithNoRetry(
            int resultSheetId,
            List<Forecast> forecasts
    ) {
        log.debug("Start updating the result in the spreadsheet. " +
                        "[spreadsheet-id={}, result-sheet-id={}, result-offset-y={}]",
                args.spreadsheetId(), resultSheetId, args.resultOffsetY());

        final List<RowData> rows = Columns.collectRows(forecasts);
        final UpdateCellsRequest updateCellsRequest = new UpdateCellsRequest()
                .setFields("*")
                .setRows(rows)
                .setStart(new GridCoordinate()
                        .setSheetId(resultSheetId)
                        .setRowIndex(args.resultOffsetY())
                        .setColumnIndex(0));

        final Request request = new Request().setUpdateCells(updateCellsRequest);
        final BatchUpdateSpreadsheetRequest batchUpdateSpreadsheetRequest =
                new BatchUpdateSpreadsheetRequest().setRequests(List.of(request));

        try {
            sheets.spreadsheets()
                    .batchUpdate(args.spreadsheetId(), batchUpdateSpreadsheetRequest)
                    .execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
