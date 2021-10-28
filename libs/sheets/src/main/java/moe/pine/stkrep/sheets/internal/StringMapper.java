package moe.pine.stkrep.sheets.internal;

import com.google.api.services.sheets.v4.model.ExtendedValue;
import moe.pine.stkrep.sheets.Forecast;
import moe.pine.stkrep.sheets.mapper.Mapper;

import java.util.function.Function;

public record StringMapper(
        Function<Forecast, String> selector
) implements Mapper {
    @Override
    public ExtendedValue mapValue(Forecast forecast) {
        return new ExtendedValue().setStringValue(selector.apply(forecast));
    }
}
