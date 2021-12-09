package moe.pine.stkrep.report.color;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BackgroundColor implements Color {
    YELLOW("#fff2cc"),
    ;

    private final ColorDelegate delegate;

    BackgroundColor(String hex) {
        this(ColorDelegate.decode(hex));
    }

    @Override
    public float getRed() {
        return delegate.getRed();
    }

    @Override
    public float getGreen() {
        return delegate.getGreen();
    }

    @Override
    public float getBlue() {
        return delegate.getBlue();
    }

    @Override
    public float getAlpha() {
        return delegate.getAlpha();
    }
}
