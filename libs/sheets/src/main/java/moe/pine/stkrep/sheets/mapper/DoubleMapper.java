package moe.pine.stkrep.sheets.mapper;

import com.google.api.services.sheets.v4.model.CellFormat;
import com.google.api.services.sheets.v4.model.ExtendedValue;
import com.google.api.services.sheets.v4.model.NumberFormat;
import lombok.RequiredArgsConstructor;
import moe.pine.stkrep.report.ForecastReport;
import moe.pine.stkrep.sheets.internal.BaseStyler;

import java.util.function.Function;

@RequiredArgsConstructor
public class DoubleMapper implements Mapper {
    private final Function<ForecastReport, Double> selector;
    private final String pattern;

    @Override
    public ExtendedValue mapValue(ForecastReport report) {
        final Double value = selector.apply(report);
        if (value == null || Double.isNaN(value)) {
            return new ExtendedValue().setNumberValue(null);
        }

        return new ExtendedValue().setNumberValue(value);
    }

    @Override
    public CellFormat mapFormat(BaseStyler styler, ForecastReport report) {
        return styler.cellFormat()
                .setNumberFormat(new NumberFormat()
                        .setType("NUMBER")
                        .setPattern(pattern));
    }
}
