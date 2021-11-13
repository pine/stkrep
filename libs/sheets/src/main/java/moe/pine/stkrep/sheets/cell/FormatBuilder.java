package moe.pine.stkrep.sheets.cell;

import com.google.api.services.sheets.v4.model.CellFormat;
import moe.pine.stkrep.report.color.Color;
import org.springframework.lang.Nullable;

public interface FormatBuilder {
    FormatBuilder backgroundColor(@Nullable Color color);

    FormatBuilder textForegroundColor(@Nullable Color color);

    FormatBuilder textBold(@Nullable Boolean bold);

    FormatBuilder numberFormatType(@Nullable String type);

    FormatBuilder numberFormatPattern(@Nullable String pattern);

    CellFormat build();
}
