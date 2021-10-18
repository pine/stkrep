package moe.pine.stkrep.sheets.forecast.internal;

import com.google.api.services.sheets.v4.model.CellFormat;
import com.google.api.services.sheets.v4.model.TextFormat;
import moe.pine.stkrep.format.BackgroundColors;
import moe.pine.stkrep.format.ForegroundColors;
import moe.pine.stkrep.sheets.common.Colors;
import moe.pine.stkrep.sheets.forecast.Forecast;

public record BaseStyler(
        Forecast forecast
) {
    public CellFormat cellFormat() {
        final CellFormat cellFormat = new CellFormat()
                .setTextFormat(textFormat());

        if (isHighlighted()) {
            cellFormat.setBackgroundColor(Colors.toSheetsColor(BackgroundColors.YELLOW));
        }

        return cellFormat;
    }

    public TextFormat textFormat() {
        return new TextFormat()
                .setForegroundColor(Colors.toSheetsColor(ForegroundColors.BLACK))
                .setBold(isHighlighted());
    }

    boolean isHighlighted() {
        return ForegroundColors.RED.equals(forecast.signal().color()) &&
                !LevelTexts.HIGH_PRICE.equals(forecast.level().text());
    }
}

