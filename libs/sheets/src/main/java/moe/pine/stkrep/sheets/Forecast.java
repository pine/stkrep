package moe.pine.stkrep.sheets;

import moe.pine.stkrep.format.FormattedText;

public record Forecast(
        int code,
        String uri,
        String name,
        int price,
        int marketCapitalization,
        FormattedText signal,
        FormattedText level,
        FormattedText rating,
        FormattedText forecastByAnalyst,
        FormattedText forecastByPer,
        FormattedText forecastByPbr,
        String per,
        String pbr,
        String dividendYield,
        String equityRatio
) {
}
