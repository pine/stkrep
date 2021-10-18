package moe.pine.stkrep.sheets.forecast.internal;

import com.google.api.services.sheets.v4.model.CellFormat;
import com.google.api.services.sheets.v4.model.ExtendedValue;
import com.google.api.services.sheets.v4.model.TextFormat;
import moe.pine.stkrep.format.FormattedText;
import moe.pine.stkrep.sheets.common.Colors;
import moe.pine.stkrep.sheets.forecast.Forecast;

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
                .setForegroundColor(Colors.toSheetsColor(formattedText.color()));

        return baseStyler.cellFormat()
                .setTextFormat(textFormat);
    }
}
