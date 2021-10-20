package moe.pine.stkrep.sheets;

import moe.pine.stkrep.format.FormattedText;

public record Forecast(
        int code,
        String uri,
        String name,
        Integer price,
        FormattedText signal,
        FormattedText level,
        FormattedText rating,
        FormattedText forecastByAnalyst,
        FormattedText forecastByPer,
        FormattedText forecastByPbr
) {
}
