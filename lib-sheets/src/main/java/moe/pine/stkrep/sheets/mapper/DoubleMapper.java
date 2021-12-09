package moe.pine.stkrep.sheets.mapper;

import com.google.api.services.sheets.v4.model.CellFormat;
import com.google.api.services.sheets.v4.model.ExtendedValue;
import moe.pine.stkrep.report.Report;
import moe.pine.stkrep.sheets.cell.FormatBuilder;
import moe.pine.stkrep.sheets.cell.ValueBuilder;


public class DoubleMapper<R extends Report> extends AbstractMapper<R, Double> {
    private final String pattern;

    public DoubleMapper(
            Selector<R, Double> selector,
            String pattern
    ) {
        super(selector);
        this.pattern = pattern;
    }

    @Override
    protected ExtendedValue onCreateValue(Double value, ValueBuilder builder) {
        if (value == null || Double.isNaN(value)) {
            return builder.build();
        }

        return builder.numberValue(value).build();
    }

    @Override
    protected CellFormat onCreateFormat(Double value, FormatBuilder builder) {
        return builder.numberFormatType("NUMBER")
                .numberFormatPattern(pattern)
                .build();
    }
}
