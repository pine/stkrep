package moe.pine.stkrep.sheets.internal;

import com.google.api.services.sheets.v4.model.ExtendedValue;
import lombok.RequiredArgsConstructor;
import moe.pine.stkrep.report.ForecastReport;
import moe.pine.stkrep.sheets.mapper.Mapper;

import java.util.function.Function;

@RequiredArgsConstructor
public class HyperlinkMapper implements Mapper {
    private final Function<ForecastReport, String> uriSelector;
    private final String friendlyName;

    @Override
    public ExtendedValue mapValue(ForecastReport report) {
        final String formula = String.format("=HYPERLINK(\"%s\", \"%s\")", uriSelector.apply(report), friendlyName);
        return new ExtendedValue().setFormulaValue(formula);
    }
}
