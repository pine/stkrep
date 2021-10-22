package moe.pine.stkrep.sheets.internal;

import com.google.api.services.sheets.v4.model.ExtendedValue;
import lombok.RequiredArgsConstructor;
import moe.pine.stkrep.sheets.Forecast;

import java.util.function.Function;

@RequiredArgsConstructor
public class IntegerMapper implements Mapper {
    private final Function<Forecast, Integer> selector;

    @Override
    public ExtendedValue mapValue(Forecast forecast) {
        final Integer value = selector.apply(forecast);
        if (value == null) {
            return new ExtendedValue().setNumberValue(null);
        }

        return new ExtendedValue().setNumberValue(value.doubleValue());
    }
}
