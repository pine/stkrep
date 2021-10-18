package moe.pine.stkrep.sheets;

import com.google.api.services.sheets.v4.model.CellData;
import com.google.api.services.sheets.v4.model.RowData;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class ForecastColumns {
    public static final List<ForecastColumn> COLUMNS = List.of(ForecastColumn.values());
    public static final int NUMBER_OF_COLUMNS = COLUMNS.size();
    public static final int MAX_NUMBER_OF_COLUMNS = 1_000;

    public List<RowData> collect(
            List<Forecast> forecasts
    ) {
        return forecasts.stream()
                .map(forecast -> {
                    final List<CellData> cells =
                            COLUMNS.stream()
                                    .map(column -> column.mapCell(forecast))
                                    .toList();

                    return new RowData().setValues(cells);
                })
                .toList();
    }
}
