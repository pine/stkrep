package moe.pine.stkrep.models;

import lombok.experimental.UtilityClass;
import moe.pine.stkrep.kabuyoho.models.Report;
import moe.pine.stkrep.sheets.Forecast;

@UtilityClass
public class Forecasts {
    public static Forecast of(Report report) {
        return new Forecast(
                report.code(),
                report.price()
        );
    }
}
