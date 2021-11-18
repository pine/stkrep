package moe.pine.stkrep.sheets.cell;

import moe.pine.stkrep.report.Report;
import moe.pine.stkrep.report.color.BackgroundColor;
import moe.pine.stkrep.report.color.ForegroundColor;

public class DefaultFormatter<R extends Report> implements Formatter<R> {
    static final DefaultFormatter<?> INSTANCE = new DefaultFormatter<>();

    @Override
    public void run(R report, FormatBuilder builder) {
        builder.textForegroundColor(ForegroundColor.BLACK);

        if (report.isHighlighted()) {
            builder.backgroundColor(BackgroundColor.YELLOW)
                    .textBold(true);
        }
    }
}
