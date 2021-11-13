package moe.pine.stkrep.sheets.internal;

import com.google.api.services.sheets.v4.model.CellData;
import lombok.RequiredArgsConstructor;
import moe.pine.stkrep.report.ForecastReport;
import moe.pine.stkrep.sheets.mapper.DoubleMapper;
import moe.pine.stkrep.sheets.mapper.IntegerMapper;
import moe.pine.stkrep.sheets.mapper.Mapper;
import moe.pine.stkrep.sheets.mapper.StringMapper;
import moe.pine.stkrep.sheets.mapper.TextEnumMapper;

@RequiredArgsConstructor
public enum Column {
    CODE(new IntegerMapper(ForecastReport::code, "####")),
    NAME(new StringMapper(ForecastReport::name)),
    PRICE(new IntegerMapper(ForecastReport::price, "###,###,### \"円\"")),
    MARKET_CAPITALIZATION(new IntegerMapper(ForecastReport::marketCapitalization, "###,###,### \"億円\"")),
    TREND_SIGNAL(new TextEnumMapper(ForecastReport::trendSignal)),
    RISK_ON(new TextEnumMapper(ForecastReport::riskOn)),
    RATING(new TextEnumMapper(ForecastReport::rating)),
    PRICE_BY_ANALYST(new TextEnumMapper(ForecastReport::priceByAnalyst)),
    PRICE_BY_PBR(new TextEnumMapper(ForecastReport::priceByPbr)),
    PRICE_BY_PER(new TextEnumMapper(ForecastReport::priceByPer)),
    PER(new DoubleMapper(ForecastReport::per, "##0.0 \"倍\"")),
    PBR(new DoubleMapper(ForecastReport::pbr, "##0.00 \"倍\"")),
    DIVIDEND_YIELD(new DoubleMapper(ForecastReport::dividendYield, "##0.00 %")),
    ROA(new DoubleMapper(ForecastReport::roa, "###,##0.00 %")),
    ROE(new DoubleMapper(ForecastReport::roe, "###,##0.00 %")),
    EQUITY_RATIO(new DoubleMapper(ForecastReport::equityRatio, "##0.0 %")),
    URI(new HyperlinkMapper(ForecastReport::uri, HyperlinkTexts.KABUYOHO)),
    ;

    private final Mapper mapper;

    public CellData map(ForecastReport report) {
        return mapper.map(report);
    }
}
