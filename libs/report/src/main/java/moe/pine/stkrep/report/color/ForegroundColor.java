package moe.pine.stkrep.report.color;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ForegroundColor implements Color {
    BLACK("#000000"),
    RED("#ea4335"),
    GREEN("#34a853"),
    ;

    private final ColorDelegate delegate;

    ForegroundColor(String hex) {
        this(ColorDelegate.of(hex));
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
}
