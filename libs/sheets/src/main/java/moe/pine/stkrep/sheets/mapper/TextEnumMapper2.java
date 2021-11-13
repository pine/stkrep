package moe.pine.stkrep.sheets.mapper;

import com.google.api.services.sheets.v4.model.CellFormat;
import com.google.api.services.sheets.v4.model.ExtendedValue;
import moe.pine.stkrep.report.Report;
import moe.pine.stkrep.report.item.TextEnum;
import moe.pine.stkrep.sheets.cell.FormatBuilder;
import moe.pine.stkrep.sheets.cell.ValueBuilder;

public class TextEnumMapper2<R extends Report> extends AbstractMapper<R, TextEnum> {
    public TextEnumMapper2(Selector<R, TextEnum> selector) {
        super(selector);
    }

    @Override
    protected ExtendedValue buildValue(TextEnum item, ValueBuilder builder) {
        return builder.stringValue(item.getOutputText()).build();
    }

    @Override
    protected CellFormat buildFormat(TextEnum item, FormatBuilder builder) {
        return builder.textForegroundColor(item.getColor()).build();
    }
}
