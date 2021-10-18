package moe.pine.stkrep.sheets;

import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class ForecastColumns {
    public final List<ForecastColumn> ITEMS = List.of(ForecastColumn.values());
    public final int SIZE = ITEMS.size();

    public List<List<Object>> collectValues(List<Forecast> forecasts) {
        return forecasts.stream()
                .map(forecast -> ITEMS.stream()
                        .map(column -> column.mapValue(forecast))
                        .toList())
                .toList();
    }
}
