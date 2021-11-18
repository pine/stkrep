package moe.pine.stkrep.sheets.mapper;

import com.google.api.services.sheets.v4.model.CellData;
import com.google.api.services.sheets.v4.model.CellFormat;
import com.google.api.services.sheets.v4.model.ExtendedValue;
import lombok.RequiredArgsConstructor;
import moe.pine.stkrep.report.Report;
import moe.pine.stkrep.sheets.cell.DefaultFormatBuilder;
import moe.pine.stkrep.sheets.cell.DefaultValueBuilder;
import moe.pine.stkrep.sheets.cell.FormatBuilder;
import moe.pine.stkrep.sheets.cell.Formatter;
import moe.pine.stkrep.sheets.cell.ValueBuilder;

@RequiredArgsConstructor
public abstract class AbstractMapper<R extends Report, V> implements Mapper<R> {
    private final Selector<R, V> selector;
    private final Formatter<R> initialFormatter;

    protected AbstractMapper(Selector<R, V> selector) {
        this(selector, Formatter.of());
    }

    @Override
    public CellData map(R report) {
        final FormatBuilder formatBuilder = new DefaultFormatBuilder();
        initialFormatter.run(report, formatBuilder);

        final V value = selector.select(report);
        return new CellData()
                .setUserEnteredValue(onCreateValue(value, new DefaultValueBuilder()))
                .setUserEnteredFormat(onCreateFormat(value, formatBuilder));
    }

    protected abstract ExtendedValue onCreateValue(V value, ValueBuilder builder);

    protected abstract CellFormat onCreateFormat(V value, FormatBuilder builder);
}
