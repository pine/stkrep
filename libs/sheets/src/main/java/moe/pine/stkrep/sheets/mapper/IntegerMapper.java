package moe.pine.stkrep.sheets.mapper;

import com.google.api.services.sheets.v4.model.CellFormat;
import com.google.api.services.sheets.v4.model.ExtendedValue;
import com.google.api.services.sheets.v4.model.NumberFormat;
import lombok.RequiredArgsConstructor;
import moe.pine.stkrep.report.Report;
import moe.pine.stkrep.sheets.internal.BaseStyler;

import java.util.function.Function;

@RequiredArgsConstructor
public class IntegerMapper implements Mapper {
    private final Function<Report, Integer> selector;
    private final String pattern;

    @Override
    public ExtendedValue mapValue(Report report) {
        final Integer value = selector.apply(report);
        if (value == null || value == 0) {
            return new ExtendedValue().setNumberValue(null);
        }

        return new ExtendedValue().setNumberValue(value.doubleValue());
    }

    @Override
    public CellFormat mapFormat(BaseStyler styler, Report report) {
        return styler.cellFormat()
                .setNumberFormat(new NumberFormat()
                        .setType("NUMBER")
                        .setPattern(pattern));
    }
}
