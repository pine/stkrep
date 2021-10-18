package moe.pine.stkrep.sheets.forecast;

import com.google.api.services.sheets.v4.model.CellData;
import com.google.api.services.sheets.v4.model.CellFormat;
import com.google.api.services.sheets.v4.model.ExtendedValue;
import com.google.api.services.sheets.v4.model.TextFormat;
import lombok.experimental.UtilityClass;
import moe.pine.stkrep.format.FormattedText;
import moe.pine.stkrep.sheets.internal.Colors;

public enum ForecastColumn {
    CODE {
        @Override
        ExtendedValue mapValue(Forecast forecast) {
            return new ExtendedValue().setNumberValue((double) forecast.code());
        }

        @Override
        CellFormat mapFormat(Forecast forecast) {
            final TextFormat textFormat = new TextFormat()
                    .setBold(false)
                    .setForegroundColor(Colors.DEFAULT);
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
            final TextFormat textFormat = new TextFormat()
                    .setBold(false)
                    .setForegroundColor(Colors.DEFAULT);
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
                    .setForegroundColor(Colors.DEFAULT);
            return new CellFormat().setTextFormat(textFormat);
        }
    },
    SIGNAL {
        @Override
        ExtendedValue mapValue(Forecast forecast) {
            return FormattedTextHelper.mapValue(forecast.signal());
        }

        @Override
        CellFormat mapFormat(Forecast forecast) {
            return FormattedTextHelper.mapFormat(forecast.signal());
        }
    },
    LEVEL {
        @Override
        ExtendedValue mapValue(Forecast forecast) {
            return FormattedTextHelper.mapValue(forecast.level());
        }

        @Override
        CellFormat mapFormat(Forecast forecast) {
            return FormattedTextHelper.mapFormat(forecast.level());
        }
    },
    FORECAST_BY_ANALYST {
        @Override
        ExtendedValue mapValue(Forecast forecast) {
            return FormattedTextHelper.mapValue(forecast.forecastByAnalyst());
        }

        @Override
        CellFormat mapFormat(Forecast forecast) {
            return FormattedTextHelper.mapFormat(forecast.forecastByAnalyst());
        }
    },
    FORECAST_BY_PBR {
        @Override
        ExtendedValue mapValue(Forecast forecast) {
            return FormattedTextHelper.mapValue(forecast.forecastByPbr());
        }

        @Override
        CellFormat mapFormat(Forecast forecast) {
            return FormattedTextHelper.mapFormat(forecast.forecastByPbr());
        }
    },
    FORECAST_BY_PER {
        @Override
        ExtendedValue mapValue(Forecast forecast) {
            return FormattedTextHelper.mapValue(forecast.forecastByPer());
        }

        @Override
        CellFormat mapFormat(Forecast forecast) {
            return FormattedTextHelper.mapFormat(forecast.forecastByPer());
        }
    },
    ;

    public CellData map(Forecast forecast) {
        return new CellData()
                .setUserEnteredValue(mapValue(forecast))
                .setUserEnteredFormat(mapFormat(forecast));
    }

    abstract ExtendedValue mapValue(Forecast forecast);

    abstract CellFormat mapFormat(Forecast forecast);

    @UtilityClass
    class FormattedTextHelper {
        ExtendedValue mapValue(FormattedText formattedText) {
            if (formattedText == null) {
                return new ExtendedValue();
            }

            return new ExtendedValue().setStringValue(formattedText.text());
        }

        CellFormat mapFormat(FormattedText formattedText) {
            final TextFormat textFormat = new TextFormat().setBold(false);
            if (formattedText != null) {
                textFormat.setForegroundColor(Colors.toSheetsColor(formattedText.color()));
            }

            return new CellFormat().setTextFormat(textFormat);
        }
    }
}
