package moe.pine.stkrep.sheets.internal;

import com.google.api.services.sheets.v4.model.CellFormat;
import com.google.api.services.sheets.v4.model.ExtendedValue;
import com.google.api.services.sheets.v4.model.TextFormat;
import moe.pine.stkrep.format.FormattedText;
import moe.pine.stkrep.report.Report;
import moe.pine.stkrep.sheets.mapper.Mapper;

import java.util.function.Function;

public record FormattedTextMapper(
        Function<Report, FormattedText> selector
) implements Mapper {
    @Override
    public ExtendedValue mapValue(Report report) {
        final FormattedText formattedText = selector.apply(report);
        return new ExtendedValue().setStringValue(formattedText.text());
    }

    @Override
    public CellFormat mapFormat(BaseStyler baseStyler, Report report) {
        final FormattedText formattedText = selector.apply(report);
        final TextFormat textFormat = baseStyler.textFormat()
                .setForegroundColor(Colors.of(formattedText.color()));

        return baseStyler.cellFormat().setTextFormat(textFormat);
    }
}
