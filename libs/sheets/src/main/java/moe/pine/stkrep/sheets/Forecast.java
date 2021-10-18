package moe.pine.stkrep.sheets;

import org.springframework.lang.Nullable;

public record Forecast(
        int code,
        @Nullable String name,
        @Nullable Integer price
) {
}
