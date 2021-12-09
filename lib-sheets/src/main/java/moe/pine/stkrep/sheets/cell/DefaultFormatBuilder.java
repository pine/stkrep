package moe.pine.stkrep.sheets.cell;

import com.google.api.services.sheets.v4.model.CellFormat;
import com.google.api.services.sheets.v4.model.NumberFormat;
import com.google.api.services.sheets.v4.model.TextFormat;
import moe.pine.stkrep.report.color.Color;
import org.springframework.lang.Nullable;

public class DefaultFormatBuilder implements FormatBuilder {
    @Nullable
    private Color backgroundColor;

    @Nullable
    private Color textForegroundColor;

    @Nullable
    private Boolean textBold;

    @Nullable
    private String numberFormatType;

    @Nullable
    private String numberFormatPattern;

    @Override
    public FormatBuilder backgroundColor(@Nullable Color color) {
        backgroundColor = color;
        return this;
    }

    @Override
    public FormatBuilder textForegroundColor(@Nullable Color color) {
        textForegroundColor = color;
        return this;
    }

    @Override
    public FormatBuilder textBold(@Nullable Boolean bold) {
        textBold = bold;
        return this;
    }

    @Override
    public FormatBuilder numberFormatType(@Nullable String type) {
        numberFormatType = type;
        return this;
    }

    @Override
    public FormatBuilder numberFormatPattern(@Nullable String pattern) {
        numberFormatPattern = pattern;
        return this;
    }

    @Override
    public CellFormat build() {
        return new CellFormat()
                .setBackgroundColor(SheetsColors.ofNullable(backgroundColor))
                .setNumberFormat(
                        new NumberFormat()
                                .setType(numberFormatType)
                                .setPattern(numberFormatPattern))
                .setTextFormat(
                        new TextFormat()
                                .setForegroundColor(SheetsColors.ofNullable(textForegroundColor))
                                .setBold(textBold));
    }
}
