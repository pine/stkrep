package moe.pine.stkrep.report;

import moe.pine.stkrep.format.FormattedText;
import moe.pine.stkrep.report.text.Rating;
import moe.pine.stkrep.report.text.RiskOn;
import moe.pine.stkrep.report.text.TrendSignal;

/**
 * @param marketCapitalization 時価総額
 * @param trendSignal          トレンドシグナル
 */
public record Forecast(
        int code,
        String uri,
        String name,
        int price,
        int marketCapitalization,
        TrendSignal trendSignal,
        RiskOn riskOn,
        Rating rating,
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
