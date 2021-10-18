package moe.pine.stkrep.sheets.forecast.internal;

import com.google.api.services.sheets.v4.model.ExtendedValue;
import moe.pine.stkrep.sheets.forecast.Forecast;

import java.util.function.Function;

public record HyperLinkMapper(
        Function<Forecast, String> selector
) implements Mapper {
    @Override
    public ExtendedValue mapValue(Forecast forecast) {
        return new ExtendedValue().setFormulaValue("=HYPERLINK(\"" + selector.apply(forecast) + "\", \"株予報\")");
    }
}
