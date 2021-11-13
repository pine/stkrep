package moe.pine.stkrep.sheets.mapper;

import com.google.api.services.sheets.v4.model.CellFormat;
import com.google.api.services.sheets.v4.model.ExtendedValue;
import moe.pine.stkrep.report.Report;
import moe.pine.stkrep.sheets.cell.FormatBuilder;
import moe.pine.stkrep.sheets.cell.ValueBuilder;

public class StringMapper2<R extends Report> extends AbstractMapper<R, String> {
    public StringMapper2(Selector<R, String> selector) {
        super(selector);
    }

    @Override
    protected ExtendedValue buildValue(String value, ValueBuilder builder) {
        return builder.stringValue(value).build();
    }

    @Override
    protected CellFormat buildFormat(String value, FormatBuilder builder) {
        return builder.build();
    }
}
