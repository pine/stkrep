package moe.pine.stkrep.sheets.internal;

import com.google.api.services.sheets.v4.model.ExtendedValue;
import moe.pine.stkrep.report.Forecast;
import moe.pine.stkrep.report.TextEnum;
import moe.pine.stkrep.sheets.mapper.Mapper;

import java.util.function.Function;

public record TextEnumMapper(
        Function<Forecast, TextEnum> selector
) implements Mapper {
    @Override
    public ExtendedValue mapValue(Forecast forecast) {
        return new ExtendedValue().setStringValue(selector.apply(forecast).getText());
    }
}
