package moe.pine.stkrep.sheets;

public enum ForecastColumn {
    CODE {
        @Override
        Object mapValue(Forecast forecast) {
            return forecast.code();
        }
    },
    PRICE {
        @Override
        Object mapValue(Forecast forecast) {
            return forecast.price();
        }
    },
    ;

    abstract Object mapValue(Forecast forecast);
}
