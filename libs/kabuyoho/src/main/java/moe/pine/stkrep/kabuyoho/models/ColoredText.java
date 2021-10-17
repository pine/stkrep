package moe.pine.stkrep.kabuyoho.models;

import org.springframework.lang.Nullable;

public record ColoredText(
        String text,
        @Nullable Color color
) {
}
