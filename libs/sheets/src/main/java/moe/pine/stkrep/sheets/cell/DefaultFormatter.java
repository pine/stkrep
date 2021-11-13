package moe.pine.stkrep.sheets.cell;

import moe.pine.stkrep.report.Report;
import moe.pine.stkrep.report.color.ForegroundColor;

public class DefaultFormatter<R extends Report> implements Formatter<R> {
    @Override
    public void run(R report, FormatBuilder builder) {
        builder.textForegroundColor(ForegroundColor.BLACK)
                .textBold(report.isHighlighted());
    }
}
