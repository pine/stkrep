package moe.pine.stkrep.sheets.forecast.internal;

import com.google.api.services.sheets.v4.model.CellData;
import com.google.api.services.sheets.v4.model.RowData;
import lombok.experimental.UtilityClass;
import moe.pine.stkrep.sheets.forecast.Forecast;

import java.util.List;

@UtilityClass
public class Columns {
    public static final List<Column> COLUMNS = List.of(Column.values());
    public static final int NUMBER_OF_COLUMNS = COLUMNS.size();
    public static final int MAX_NUMBER_OF_COLUMNS = 1_000;

    public List<RowData> collectRows(
            List<Forecast> forecasts
    ) {
        return forecasts.stream()
                .map(forecast -> {
                    final List<CellData> cells =
                            COLUMNS.stream()
                                    .map(column -> column.map(forecast))
                                    .toList();

                    return new RowData().setValues(cells);
                })
                .toList();
    }
}
