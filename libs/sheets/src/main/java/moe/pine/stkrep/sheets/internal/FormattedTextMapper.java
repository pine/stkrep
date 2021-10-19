package moe.pine.stkrep.sheets.internal;

import com.google.api.services.sheets.v4.model.CellFormat;
import com.google.api.services.sheets.v4.model.ExtendedValue;
import com.google.api.services.sheets.v4.model.TextFormat;
import moe.pine.stkrep.format.FormattedText;
import moe.pine.stkrep.sheets.Forecast;

import java.util.function.Function;

public record FormattedTextMapper(
        Function<Forecast, FormattedText> selector
) implements Mapper {
    @Override
    public ExtendedValue mapValue(Forecast forecast) {
        final FormattedText formattedText = selector.apply(forecast);
        return new ExtendedValue().setStringValue(formattedText.text());
    }

    @Override
    public CellFormat mapFormat(BaseStyler baseStyler, Forecast forecast) {
        final FormattedText formattedText = selector.apply(forecast);
        final TextFormat textFormat = baseStyler.textFormat()
                .setForegroundColor(Colors.of(formattedText.color()));

        return baseStyler.cellFormat().setTextFormat(textFormat);
    }
}
