package moe.pine.stkrep.sheets.mapper;

import com.google.api.services.sheets.v4.model.CellFormat;
import com.google.api.services.sheets.v4.model.ExtendedValue;
import moe.pine.stkrep.report.Report;
import moe.pine.stkrep.sheets.cell.FormatBuilder;
import moe.pine.stkrep.sheets.cell.ValueBuilder;


public class HyperlinkMapper<R extends Report> extends AbstractMapper<R, String> {
    private final String friendlyName;

    public HyperlinkMapper(
            Selector<R, String> selector,
            String friendlyName
    ) {
        super(selector);
        this.friendlyName = friendlyName;
    }

    @Override
    protected ExtendedValue onCreateValue(String value, ValueBuilder builder) {
        final String formula = String.format("=HYPERLINK(\"%s\", \"%s\")", value, friendlyName);
        return builder.formulaValue(formula).build();
    }

    @Override
    protected CellFormat onCreateFormat(String value, FormatBuilder builder) {
        return builder.build();
    }
}
