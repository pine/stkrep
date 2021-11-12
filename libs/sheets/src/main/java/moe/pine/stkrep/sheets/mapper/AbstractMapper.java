package moe.pine.stkrep.sheets.mapper;

import com.google.api.services.sheets.v4.model.CellData;
import com.google.api.services.sheets.v4.model.CellFormat;
import com.google.api.services.sheets.v4.model.ExtendedValue;
import lombok.RequiredArgsConstructor;
import moe.pine.stkrep.sheets.cell.DefaultFormatBuilder;
import moe.pine.stkrep.sheets.cell.DefaultValueBuilder;
import moe.pine.stkrep.sheets.cell.FormatBuilder;
import moe.pine.stkrep.sheets.cell.Formatter;
import moe.pine.stkrep.sheets.cell.ValueBuilder;

@RequiredArgsConstructor
public abstract class AbstractMapper<R, I> implements Mapper2<R> {
    private final Selector<R, I> selector;
    private final Formatter<R> initialFormatter;

    @Override
    public CellData map(R report) {
        final FormatBuilder formatBuilder = new DefaultFormatBuilder();
        initialFormatter.run(report, formatBuilder);

        final I item = selector.select(report);
        return new CellData()
                .setUserEnteredValue(onCreateValue(item, new DefaultValueBuilder()))
                .setUserEnteredFormat(onCreateFormat(item, formatBuilder));
    }

    protected abstract ExtendedValue onCreateValue(I item, ValueBuilder builder);

    protected abstract CellFormat onCreateFormat(I item, FormatBuilder builder);
}
