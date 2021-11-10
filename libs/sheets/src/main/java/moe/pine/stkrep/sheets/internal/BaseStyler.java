package moe.pine.stkrep.sheets.internal;

import com.google.api.services.sheets.v4.model.CellFormat;
import com.google.api.services.sheets.v4.model.TextFormat;
import com.google.common.annotations.VisibleForTesting;
import lombok.RequiredArgsConstructor;
import moe.pine.stkrep.format.BackgroundColors;
import moe.pine.stkrep.format.ForegroundColors;
import moe.pine.stkrep.report.Report;
import moe.pine.stkrep.report.item.Rating;
import moe.pine.stkrep.report.item.RiskOn;
import moe.pine.stkrep.report.item.TrendSignal;

@RequiredArgsConstructor
public class BaseStyler {
    private final Report report;

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
        final boolean isBuy = TrendSignal.BUY.contains(report.trendSignal());
        final boolean lowLevel = report.riskOn() == RiskOn.BOTTOM_PRICE_ZONE;
        final boolean lowRating = Rating.SELL.contains(report.rating());

        return isBuy && lowLevel && !lowRating;
    }
}

