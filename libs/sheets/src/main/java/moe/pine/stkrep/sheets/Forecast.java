package moe.pine.stkrep.sheets;

import org.springframework.lang.Nullable;

public record Forecast(
        @Nullable Integer price
) {
}
