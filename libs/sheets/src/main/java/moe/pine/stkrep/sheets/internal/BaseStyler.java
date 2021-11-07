package moe.pine.stkrep.sheets.internal;

import com.google.api.services.sheets.v4.model.CellFormat;
import com.google.api.services.sheets.v4.model.TextFormat;
import com.google.common.annotations.VisibleForTesting;
import lombok.RequiredArgsConstructor;
import moe.pine.stkrep.format.BackgroundColors;
import moe.pine.stkrep.format.ForegroundColors;
import moe.pine.stkrep.report.Forecast;
import moe.pine.stkrep.report.text.TrendSignal;

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
        final boolean isBuy = TrendSignal.BUY.contains(forecast.trendSignal());
        final boolean lowLevel = LevelTexts.LOW.equals(forecast.level().text());
        final boolean lowRating = ForegroundColors.GREEN.equals(forecast.rating().color());
        final boolean lowPriceForecast =
                ForegroundColors.RED.equals(forecast.forecastByAnalyst().color()) ||
                        ForegroundColors.RED.equals(forecast.forecastByPbr().color()) ||
                        ForegroundColors.RED.equals(forecast.forecastByPer().color());

        return isBuy && lowLevel && !lowRating && lowPriceForecast;
    }
}

