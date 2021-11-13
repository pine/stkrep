package moe.pine.stkrep.sheets.mapper;

import com.google.api.services.sheets.v4.model.ExtendedValue;
import moe.pine.stkrep.report.ForecastReport;

import java.util.function.Function;

public record StringMapper(
        Function<ForecastReport, String> selector
) implements Mapper {
    @Override
    public ExtendedValue mapValue(ForecastReport report) {
        return new ExtendedValue().setStringValue(selector.apply(report));
    }
}
