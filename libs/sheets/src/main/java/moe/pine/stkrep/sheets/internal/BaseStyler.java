package moe.pine.stkrep.sheets.internal;

import com.google.api.services.sheets.v4.model.CellFormat;
import com.google.api.services.sheets.v4.model.TextFormat;
import com.google.common.annotations.VisibleForTesting;
import lombok.RequiredArgsConstructor;
import moe.pine.stkrep.format.BackgroundColors;
import moe.pine.stkrep.format.ForegroundColors;
import moe.pine.stkrep.sheets.Forecast;

@RequiredArgsConstructor
public class BaseStyler {
    private final Forecast forecast;

    public CellFormat cellFormat() {
        final CellFormat cellFormat = new CellFormat()
                .setTextFormat(textFormat());

        if (isHighlighted()) {
            cellFormat.setBackgroundColor(Colors.of(BackgroundColors.YELLOW));
        }

        return cellFormat;
    }

    public TextFormat textFormat() {
        return new TextFormat()
                .setForegroundColor(Colors.of(ForegroundColors.BLACK))
                .setBold(isHighlighted());
    }

    @VisibleForTesting
    boolean isHighlighted() {
        final boolean buyingSignal = ForegroundColors.RED.equals(forecast.signal().color());
        final boolean highPriceLevel = LevelTexts.HIGH_PRICE.equals(forecast.level().text());
        final boolean lowPriceForecast =
                ForegroundColors.RED.equals(forecast.forecastByAnalyst().color()) ||
                        ForegroundColors.RED.equals(forecast.forecastByPbr().color()) ||
                        ForegroundColors.RED.equals(forecast.forecastByPer().color());

        return buyingSignal && !highPriceLevel && lowPriceForecast;
    }
}

