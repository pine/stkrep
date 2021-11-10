package moe.pine.stkrep.sheets.internal;

import com.google.api.services.sheets.v4.model.CellData;
import com.google.api.services.sheets.v4.model.RowData;
import lombok.experimental.UtilityClass;
import moe.pine.stkrep.report.Report;

import java.util.List;
import java.util.stream.Stream;

@UtilityClass
public class Columns {
    public static final int NUMBER_OF_COLUMNS = Column.values().length;
    public static final int MAX_NUMBER_OF_COLUMNS = 1_000;

    public List<RowData> collectRows(
            List<Report> reports
    ) {
        return reports.stream()
                .map(forecast -> {
                    final List<CellData> cells =
                            Stream.of(Column.values())
                                    .map(column -> column.map(forecast))
                                    .toList();

                    return new RowData().setValues(cells);
                })
                .toList();
    }
}
