package moe.pine.stkrep.format;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StandardColor {
    RED(1.0f, 0.0f, 0.0f),
    GREEN(0.0f, 1.0f, 0.0f),
    BLACK(0.0f, 0.0f, 0.0f),
    ;

    private final float red;
    private final float green;
    private final float blue;
}
