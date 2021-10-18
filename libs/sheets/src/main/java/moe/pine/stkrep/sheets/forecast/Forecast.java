package moe.pine.stkrep.sheets.forecast;

import moe.pine.stkrep.format.FormattedText;

public record Forecast(
        int code,
        String uri,
        String name,
        Integer price,
        FormattedText signal,
        FormattedText level,
        FormattedText forecastByAnalyst,
        FormattedText forecastByPer,
        FormattedText forecastByPbr
) {
}
