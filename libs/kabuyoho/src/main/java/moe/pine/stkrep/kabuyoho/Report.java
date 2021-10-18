package moe.pine.stkrep.kabuyoho;

import moe.pine.stkrep.format.FormattedText;
import org.springframework.lang.Nullable;

public record Report(
        int code,
        @Nullable String name,
        @Nullable Integer price,
        @Nullable FormattedText signal,
        @Nullable FormattedText level,
        @Nullable FormattedText forecastByAnalyst,
        @Nullable FormattedText forecastByPbr,
        @Nullable FormattedText forecastByPer
) {
}
