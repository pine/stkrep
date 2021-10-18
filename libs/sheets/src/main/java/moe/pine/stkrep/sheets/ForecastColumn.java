package moe.pine.stkrep.sheets;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public enum ForecastColumn {
    CODE {
        @Override
        Object mapValue(Forecast forecast) {
            return forecast.code();
        }
    },
    NAME {
        @Override
        Object mapValue(Forecast forecast) {
            return StringUtils.defaultString(forecast.name());
        }
    },
    PRICE {
        @Override
        Object mapValue(Forecast forecast) {
            return Objects.toString(forecast.price(), "");
        }
    },
    ;

    abstract Object mapValue(Forecast forecast);
}
