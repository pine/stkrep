package moe.pine.stkrep.sheets;

import org.springframework.lang.Nullable;

public record Forecast(
        int code,
        @Nullable Integer price
) {
}
