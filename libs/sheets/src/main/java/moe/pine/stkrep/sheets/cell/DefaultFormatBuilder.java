package moe.pine.stkrep.sheets.cell;

import com.google.api.services.sheets.v4.model.CellFormat;
import com.google.api.services.sheets.v4.model.TextFormat;
import moe.pine.stkrep.report.color.Color;
import org.springframework.lang.Nullable;

public class DefaultFormatBuilder implements FormatBuilder {
    @Nullable
    private Color backgroundColor;

    @Nullable
    private Color foregroundColor;

    @Nullable
    private Boolean bold;

    public DefaultFormatBuilder backgroundColor(Color color) {
        backgroundColor = color;
        return this;
    }

    public DefaultFormatBuilder foregroundColor(Color color) {
        foregroundColor = color;
        return this;
    }

    public DefaultFormatBuilder bold(Boolean bold) {
        this.bold = bold;
        return this;
    }

    @Override
    public CellFormat build() {
        return new CellFormat()
                .setBackgroundColor(SheetsColors.ofNullable(backgroundColor))
                .setTextFormat(
                        new TextFormat()
                                .setForegroundColor(SheetsColors.ofNullable(foregroundColor))
                                .setBold(bold));
    }
}
