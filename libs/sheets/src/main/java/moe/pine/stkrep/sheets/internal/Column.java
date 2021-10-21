package moe.pine.stkrep.sheets.internal;

import com.google.api.services.sheets.v4.model.CellData;
import lombok.RequiredArgsConstructor;
import moe.pine.stkrep.sheets.Forecast;

@RequiredArgsConstructor
public enum Column {
    CODE(new IntegerMapper(Forecast::code)),
    NAME(new StringMapper(Forecast::name)),
    PRICE(new PriceMapper()),
    SIGNAL(new FormattedTextMapper(Forecast::signal)),
    LEVEL(new FormattedTextMapper(Forecast::level)),
    RATING(new FormattedTextMapper(Forecast::rating)),
    FORECAST_BY_ANALYST(new FormattedTextMapper(Forecast::forecastByAnalyst)),
    FORECAST_BY_PBR(new FormattedTextMapper(Forecast::forecastByPbr)),
    FORECAST_BY_PER(new FormattedTextMapper(Forecast::forecastByPer)),
    DIVIDEND_YIELD(new StringMapper(Forecast::dividendYield)),
    URI(new HyperlinkMapper(Forecast::uri, HyperlinkTexts.KABUYOHO)),
    ;

    private final Mapper mapper;

    public CellData map(Forecast forecast) {
        return mapper.map(forecast);
    }
}
