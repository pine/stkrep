package moe.pine.stkrep.sheets.internal;

import com.google.api.services.sheets.v4.model.CellFormat;
import com.google.api.services.sheets.v4.model.ExtendedValue;
import com.google.api.services.sheets.v4.model.TextFormat;
import moe.pine.stkrep.report.Report;
import moe.pine.stkrep.report.item.TextEnum;
import moe.pine.stkrep.sheets.mapper.Mapper;

import java.util.function.Function;

public record TextEnumMapper(
        Function<Report, TextEnum> selector
) implements Mapper {
    @Override
    public ExtendedValue mapValue(Report report) {
        return new ExtendedValue().setStringValue(selector.apply(report).getOutputText());
    }


    @Override
    public CellFormat mapFormat(BaseStyler baseStyler, Report report) {
        final TextEnum textEnum = selector.apply(report);
        final TextFormat textFormat = baseStyler.textFormat()
                .setForegroundColor(Colors.of(textEnum.getColor()));

        return baseStyler.cellFormat().setTextFormat(textFormat);
    }
}
