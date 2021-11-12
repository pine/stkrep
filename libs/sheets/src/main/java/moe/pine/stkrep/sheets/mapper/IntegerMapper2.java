package moe.pine.stkrep.sheets.mapper;

import com.google.api.services.sheets.v4.model.CellFormat;
import com.google.api.services.sheets.v4.model.ExtendedValue;
import moe.pine.stkrep.sheets.cell.FormatBuilder;
import moe.pine.stkrep.sheets.cell.Formatter;
import moe.pine.stkrep.sheets.cell.ValueBuilder;

public class IntegerMapper2<R> extends AbstractMapper<R, Integer> {
    public IntegerMapper2(Selector<R, Integer> selector, Formatter<R> initialFormatter) {
        super(selector, initialFormatter);
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
        return builder.build();
    }
}
