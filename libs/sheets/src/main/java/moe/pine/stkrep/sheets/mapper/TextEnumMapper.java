package moe.pine.stkrep.sheets.mapper;

import com.google.api.services.sheets.v4.model.CellFormat;
import com.google.api.services.sheets.v4.model.ExtendedValue;
import moe.pine.stkrep.report.Report;
import moe.pine.stkrep.report.value.TextEnum;
import moe.pine.stkrep.sheets.cell.FormatBuilder;
import moe.pine.stkrep.sheets.cell.ValueBuilder;

public class TextEnumMapper<R extends Report> extends AbstractMapper<R, TextEnum> {
    public TextEnumMapper(Selector<R, TextEnum> selector) {
        super(selector);
    }

    @Override
    protected ExtendedValue onCreateValue(TextEnum value, ValueBuilder builder) {
        return builder.stringValue(value.getOutputText()).build();
    }

    @Override
    protected CellFormat onCreateFormat(TextEnum value, FormatBuilder builder) {
        return builder.textForegroundColor(value.getColor()).build();
    }
}
