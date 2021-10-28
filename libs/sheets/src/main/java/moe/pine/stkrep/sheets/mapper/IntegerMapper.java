package moe.pine.stkrep.sheets.mapper;

import com.google.api.services.sheets.v4.model.CellFormat;
import com.google.api.services.sheets.v4.model.ExtendedValue;
import com.google.api.services.sheets.v4.model.NumberFormat;
import lombok.RequiredArgsConstructor;
import moe.pine.stkrep.sheets.Forecast;
import moe.pine.stkrep.sheets.internal.BaseStyler;
import moe.pine.stkrep.sheets.mapper.Mapper;

import java.util.function.Function;

@RequiredArgsConstructor
public class IntegerMapper implements Mapper {
    private final Function<Forecast, Integer> selector;
    private final String pattern;

    @Override
    public ExtendedValue mapValue(Forecast forecast) {
        final Integer value = selector.apply(forecast);
        if (value == null || value == 0) {
            return new ExtendedValue().setNumberValue(null);
        }

        return new ExtendedValue().setNumberValue(value.doubleValue());
    }

    @Override
    public CellFormat mapFormat(BaseStyler styler, Forecast forecast) {
        return styler.cellFormat()
                .setNumberFormat(new NumberFormat()
                        .setType("NUMBER")
                        .setPattern(pattern));
    }
}
