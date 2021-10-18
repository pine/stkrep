package moe.pine.stkrep.kabuyoho.models;

import org.springframework.lang.Nullable;

public record Report(
        int code,
        @Nullable Integer price,
        @Nullable ColoredText signal,
        @Nullable ColoredText level,
        @Nullable ColoredText forecastByAnalyst,
        @Nullable ColoredText forecastByPbr,
        @Nullable ColoredText forecastByPer
) {
}
