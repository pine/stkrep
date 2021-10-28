package moe.pine.stkrep.kabuyoho;

import moe.pine.stkrep.format.FormattedText;

public record Report(
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
        double dividendYield,
        double equityRatio
) {
}
