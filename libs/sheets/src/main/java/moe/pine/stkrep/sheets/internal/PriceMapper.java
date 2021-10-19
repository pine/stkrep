package moe.pine.stkrep.sheets.internal;

import com.google.api.services.sheets.v4.model.ExtendedValue;
import moe.pine.stkrep.sheets.Forecast;

public class PriceMapper implements Mapper {
    @Override
    public ExtendedValue mapValue(Forecast forecast) {
        final Integer price = forecast.price();
        if (price == null) {
            return new ExtendedValue();
        }

        return new ExtendedValue().setNumberValue(price.doubleValue());
    }
}

