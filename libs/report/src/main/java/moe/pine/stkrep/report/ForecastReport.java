package moe.pine.stkrep.report;

import moe.pine.stkrep.report.item.Rating;
import moe.pine.stkrep.report.item.RiskOn;
import moe.pine.stkrep.report.item.StockPrice;
import moe.pine.stkrep.report.item.TrendSignal;

/**
 * @param marketCapitalization 時価総額
 * @param trendSignal          トレンドシグナル
 * @param riskOn               リスクオン相対指数
 * @param rating               レーティング
 */
public record ForecastReport(
        int code,
        String uri,
        String name,
        int price,
        int marketCapitalization,
        TrendSignal trendSignal,
        RiskOn riskOn,
        Rating rating,
        StockPrice priceByAnalyst,
        StockPrice priceByPbr,
        StockPrice priceByPer,
        double per,
        double pbr,
        double dividendYield,
        double roa,
        double roe,
        double equityRatio
) implements Report {
    @Override
    public boolean isHighlighted() {
        return false;
    }
}
