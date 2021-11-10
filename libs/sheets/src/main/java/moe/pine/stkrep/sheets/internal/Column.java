package moe.pine.stkrep.sheets.internal;

import com.google.api.services.sheets.v4.model.CellData;
import lombok.RequiredArgsConstructor;
import moe.pine.stkrep.report.Report;
import moe.pine.stkrep.sheets.mapper.DoubleMapper;
import moe.pine.stkrep.sheets.mapper.IntegerMapper;
import moe.pine.stkrep.sheets.mapper.Mapper;

@RequiredArgsConstructor
public enum Column {
    CODE(new IntegerMapper(Report::code, "####")),
    NAME(new StringMapper(Report::name)),
    PRICE(new IntegerMapper(Report::price, "###,###,### \"円\"")),
    MARKET_CAPITALIZATION(new IntegerMapper(Report::marketCapitalization, "###,###,### \"億円\"")),
    SIGNAL(new TextEnumMapper(Report::trendSignal)),
    RISK_ON(new TextEnumMapper(Report::riskOn)),
    RATING(new TextEnumMapper(Report::rating)),
    FORECAST_BY_ANALYST(new FormattedTextMapper(Report::forecastByAnalyst)),
    FORECAST_BY_PBR(new FormattedTextMapper(Report::forecastByPbr)),
    FORECAST_BY_PER(new FormattedTextMapper(Report::forecastByPer)),
    PER(new DoubleMapper(Report::per, "##0.0 \"倍\"")),
    PBR(new DoubleMapper(Report::pbr, "##0.00 \"倍\"")),
    DIVIDEND_YIELD(new DoubleMapper(Report::dividendYield, "##0.00 %")),
    ROA(new DoubleMapper(Report::roa, "###,##0.00 %")),
    ROE(new DoubleMapper(Report::roe, "###,##0.00 %")),
    EQUITY_RATIO(new DoubleMapper(Report::equityRatio, "##0.0 %")),
    URI(new HyperlinkMapper(Report::uri, HyperlinkTexts.KABUYOHO)),
    ;

    private final Mapper mapper;

    public CellData map(Report report) {
        return mapper.map(report);
    }
}
