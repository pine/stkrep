package moe.pine.stkrep.sheets.internal;

import com.google.api.services.sheets.v4.model.CellFormat;
import com.google.api.services.sheets.v4.model.TextFormat;
import com.google.common.annotations.VisibleForTesting;
import lombok.RequiredArgsConstructor;
import moe.pine.stkrep.report.Report;
import moe.pine.stkrep.report.color.BackgroundColor;
import moe.pine.stkrep.report.color.ForegroundColor;
import moe.pine.stkrep.report.item.Rating;
import moe.pine.stkrep.report.item.RiskOn;
import moe.pine.stkrep.report.item.TrendSignal;
import moe.pine.stkrep.sheets.cell.SheetsColors;

@RequiredArgsConstructor
public class BaseStyler {
    private final Report report;

    public CellFormat cellFormat() {
        final CellFormat cellFormat = new CellFormat()
                .setTextFormat(textFormat());

        if (isHighlighted()) {
            cellFormat.setBackgroundColor(SheetsColors.of(BackgroundColor.YELLOW));
        }

        return cellFormat;
    }

    public TextFormat textFormat() {
        return new TextFormat()
                .setForegroundColor(SheetsColors.of(ForegroundColor.BLACK))
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

