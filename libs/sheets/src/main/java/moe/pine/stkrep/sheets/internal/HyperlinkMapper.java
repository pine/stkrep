package moe.pine.stkrep.sheets.internal;

import com.google.api.services.sheets.v4.model.ExtendedValue;
import lombok.RequiredArgsConstructor;
import moe.pine.stkrep.sheets.Forecast;
import moe.pine.stkrep.sheets.mapper.Mapper;

import java.util.function.Function;

@RequiredArgsConstructor
public class HyperlinkMapper implements Mapper {
    private final Function<Forecast, String> uriSelector;
    private final String friendlyName;

    @Override
    public ExtendedValue mapValue(Forecast forecast) {
        final String formula = String.format("=HYPERLINK(\"%s\", \"%s\")", uriSelector.apply(forecast), friendlyName);
        return new ExtendedValue().setFormulaValue(formula);
    }
}
