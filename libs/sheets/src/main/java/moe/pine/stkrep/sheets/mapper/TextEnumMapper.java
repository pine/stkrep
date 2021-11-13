package moe.pine.stkrep.sheets.mapper;

import com.google.api.services.sheets.v4.model.CellFormat;
import com.google.api.services.sheets.v4.model.ExtendedValue;
import com.google.api.services.sheets.v4.model.TextFormat;
import moe.pine.stkrep.report.ForecastReport;
import moe.pine.stkrep.report.item.TextEnum;
import moe.pine.stkrep.sheets.cell.SheetsColors;
import moe.pine.stkrep.sheets.internal.BaseStyler;

import java.util.function.Function;

public record TextEnumMapper(
        Function<ForecastReport, TextEnum> selector
) implements Mapper {
    @Override
    public ExtendedValue mapValue(ForecastReport report) {
        return new ExtendedValue().setStringValue(selector.apply(report).getOutputText());
    }


    @Override
    public CellFormat mapFormat(BaseStyler baseStyler, ForecastReport report) {
        final TextEnum textEnum = selector.apply(report);
        final TextFormat textFormat = baseStyler.textFormat()
                .setForegroundColor(SheetsColors.of(textEnum.getColor()));

        return baseStyler.cellFormat().setTextFormat(textFormat);
    }
}
