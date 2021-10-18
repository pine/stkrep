package moe.pine.stkrep.sheets;

import com.google.api.services.sheets.v4.model.CellData;
import com.google.api.services.sheets.v4.model.CellFormat;
import com.google.api.services.sheets.v4.model.Color;
import com.google.api.services.sheets.v4.model.ExtendedValue;
import com.google.api.services.sheets.v4.model.TextFormat;

public enum ForecastColumn {
    CODE {
        @Override
        ExtendedValue mapValue(Forecast forecast) {
            return new ExtendedValue().setNumberValue((double) forecast.code());
        }

        @Override
        CellFormat mapFormat(Forecast forecast) {
            final TextFormat textFormat = new TextFormat().setBold(false);
            return new CellFormat().setTextFormat(textFormat);
        }
    },
    NAME {
        @Override
        ExtendedValue mapValue(Forecast forecast) {
            return new ExtendedValue().setStringValue(forecast.name());
        }

        @Override
        CellFormat mapFormat(Forecast forecast) {
            final TextFormat textFormat = new TextFormat().setBold(false);
            return new CellFormat().setTextFormat(textFormat);
        }
    },
    PRICE {
        @Override
        ExtendedValue mapValue(Forecast forecast) {
            final Integer price = forecast.price();
            if (price == null) {
                return new ExtendedValue();
            }

            return new ExtendedValue().setNumberValue(price.doubleValue());
        }

        @Override
        CellFormat mapFormat(Forecast forecast) {
            final TextFormat textFormat = new TextFormat()
                    .setBold(false)
                    .setForegroundColor(new Color().setRed(1.0f));
            return new CellFormat().setTextFormat(textFormat);
        }
    },
    ;

    public CellData mapCell(Forecast forecast) {
        return new CellData()
                .setUserEnteredValue(mapValue(forecast))
                .setUserEnteredFormat(mapFormat(forecast));
    }

    abstract ExtendedValue mapValue(Forecast forecast);

    abstract CellFormat mapFormat(Forecast forecast);
}
