package moe.pine.stkrep.format;

import org.springframework.lang.Nullable;

public record FormattedText(
        String text,
        @Nullable StandardColor color
) {
}
