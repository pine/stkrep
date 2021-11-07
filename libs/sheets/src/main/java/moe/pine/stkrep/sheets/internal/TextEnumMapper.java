package moe.pine.stkrep.sheets.internal;

import com.google.api.services.sheets.v4.model.CellFormat;
import com.google.api.services.sheets.v4.model.ExtendedValue;
import com.google.api.services.sheets.v4.model.TextFormat;
import moe.pine.stkrep.report.Forecast;
import moe.pine.stkrep.report.text.TextEnum;
import moe.pine.stkrep.sheets.mapper.Mapper;

import java.util.function.Function;

public record TextEnumMapper(
        Function<Forecast, TextEnum> selector
) implements Mapper {
    @Override
    public ExtendedValue mapValue(Forecast forecast) {
        return new ExtendedValue().setStringValue(selector.apply(forecast).getText());
    }


    @Override
    public CellFormat mapFormat(BaseStyler baseStyler, Forecast forecast) {
        final TextEnum textEnum = selector.apply(forecast);
        final TextFormat textFormat = baseStyler.textFormat()
                .setForegroundColor(Colors.of(textEnum.getColor()));

        return baseStyler.cellFormat().setTextFormat(textFormat);
    }
}
