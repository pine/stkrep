package moe.pine.stkrep.report;

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
        FormattedText forecastByPbr,
        FormattedText forecastByPer,
        double per,
        double pbr,
        double roa,
        double roe,
        double dividendYield,
        double equityRatio
) {
}
