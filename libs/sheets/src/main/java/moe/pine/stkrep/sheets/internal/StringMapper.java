package moe.pine.stkrep.sheets.internal;

import com.google.api.services.sheets.v4.model.ExtendedValue;
import moe.pine.stkrep.report.Report;
import moe.pine.stkrep.sheets.mapper.Mapper;

import java.util.function.Function;

public record StringMapper(
        Function<Report, String> selector
) implements Mapper {
    @Override
    public ExtendedValue mapValue(Report report) {
        return new ExtendedValue().setStringValue(selector.apply(report));
    }
}
