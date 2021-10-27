package moe.pine.stkrep.models;

import lombok.experimental.UtilityClass;
import moe.pine.stkrep.kabuyoho.Report;
import moe.pine.stkrep.sheets.Forecast;

@UtilityClass
public class Forecasts {
    public static Forecast of(Report report) {
        return new Forecast(
                report.code(),
                report.uri(),
                report.name(),
                report.price(),
                report.marketCapitalization(),
                report.signal(),
                report.level(),
                report.rating(),
                report.forecastByAnalyst(),
                report.forecastByPbr(),
                report.forecastByPer(),
                report.per(),
                report.pbr(),
                report.dividendYield(),
                report.equityRatio()
        );
    }
}
