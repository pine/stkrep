package moe.pine.stkrep.sheets.internal;

import com.google.api.services.sheets.v4.model.CellData;
import lombok.RequiredArgsConstructor;
import moe.pine.stkrep.report.Forecast;
import moe.pine.stkrep.sheets.mapper.DoubleMapper;
import moe.pine.stkrep.sheets.mapper.IntegerMapper;
import moe.pine.stkrep.sheets.mapper.Mapper;

@RequiredArgsConstructor
public enum Column {
    CODE(new IntegerMapper(Forecast::code, "####")),
    NAME(new StringMapper(Forecast::name)),
    PRICE(new IntegerMapper(Forecast::price, "###,###,### \"円\"")),
    MARKET_CAPITALIZATION(new IntegerMapper(Forecast::marketCapitalization, "###,###,### \"億円\"")),
    SIGNAL(new TextEnumMapper(Forecast::trendSignal)),
    RISK_ON(new TextEnumMapper(Forecast::riskOn)),
    RATING(new FormattedTextMapper(Forecast::rating)),
    FORECAST_BY_ANALYST(new FormattedTextMapper(Forecast::forecastByAnalyst)),
    FORECAST_BY_PBR(new FormattedTextMapper(Forecast::forecastByPbr)),
    FORECAST_BY_PER(new FormattedTextMapper(Forecast::forecastByPer)),
    PER(new DoubleMapper(Forecast::per, "##0.0 \"倍\"")),
    PBR(new DoubleMapper(Forecast::pbr, "##0.00 \"倍\"")),
    DIVIDEND_YIELD(new DoubleMapper(Forecast::dividendYield, "##0.00 %")),
    ROA(new DoubleMapper(Forecast::roa, "###,##0.00 %")),
    ROE(new DoubleMapper(Forecast::roe, "###,##0.00 %")),
    EQUITY_RATIO(new DoubleMapper(Forecast::equityRatio, "##0.0 %")),
    URI(new HyperlinkMapper(Forecast::uri, HyperlinkTexts.KABUYOHO)),
    ;

    private final Mapper mapper;

    public CellData map(Forecast forecast) {
        return mapper.map(forecast);
    }
}
