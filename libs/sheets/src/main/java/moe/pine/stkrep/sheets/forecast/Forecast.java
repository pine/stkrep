package moe.pine.stkrep.sheets.forecast;

import moe.pine.stkrep.format.FormattedText;
import org.springframework.lang.Nullable;

public record Forecast(
        int code,
        @Nullable String name,
        @Nullable Integer price,
        @Nullable FormattedText signal,
        @Nullable FormattedText level,
        @Nullable FormattedText forecastByAnalyst,
        @Nullable FormattedText forecastByPer,
        @Nullable FormattedText forecastByPbr
) {
}
