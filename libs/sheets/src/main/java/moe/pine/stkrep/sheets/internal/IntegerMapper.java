package moe.pine.stkrep.sheets.internal;

import com.google.api.services.sheets.v4.model.ExtendedValue;
import moe.pine.stkrep.sheets.Forecast;

import java.util.function.Function;

public record IntegerMapper(
        Function<Forecast, Integer> selector
) implements Mapper {
    @Override
    public ExtendedValue mapValue(Forecast forecast) {
        final Integer value = selector.apply(forecast);
        if (value == null) {
            return new ExtendedValue().setNumberValue(null);
        }

        return new ExtendedValue().setNumberValue(value.doubleValue());
    }
}
