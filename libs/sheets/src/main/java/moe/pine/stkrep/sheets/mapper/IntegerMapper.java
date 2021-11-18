package moe.pine.stkrep.sheets.mapper;

import com.google.api.services.sheets.v4.model.CellFormat;
import com.google.api.services.sheets.v4.model.ExtendedValue;
import moe.pine.stkrep.report.Report;
import moe.pine.stkrep.sheets.cell.FormatBuilder;
import moe.pine.stkrep.sheets.cell.ValueBuilder;

public class IntegerMapper<R extends Report> extends AbstractMapper<R, Integer> {
    private final String pattern;

    public IntegerMapper(
            Selector<R, Integer> selector,
            String pattern
    ) {
        super(selector);
        this.pattern = pattern;
    }

    @Override
    protected ExtendedValue onCreateValue(Integer item, ValueBuilder builder) {
        if (item == null || item == 0) {
            return builder.build();
        }

        return builder.numberValue(item.doubleValue()).build();
    }

    @Override
    protected CellFormat onCreateFormat(Integer item, FormatBuilder builder) {
        return builder.numberFormatType("NUMBER")
                .numberFormatPattern(pattern)
                .build();
    }
}
