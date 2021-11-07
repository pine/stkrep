package moe.pine.stkrep.report.text;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import moe.pine.stkrep.report.color.Color;
import moe.pine.stkrep.report.color.ForegroundColor;

import java.util.Set;

@Getter
@RequiredArgsConstructor
public enum TrendSignal implements TextEnum {
    BUY_CHANGED("買い転換", ForegroundColor.RED),
    CONTINUE_BUY("買い継続", ForegroundColor.RED),
    NEUTRAL("ニュートラル", ForegroundColor.BLACK),
    CONTINUE_SELL("売り継続", ForegroundColor.GREEN),
    SELL_CHANGED("売り転換", ForegroundColor.GREEN),
    ;

    public static final Set<TrendSignal> BUY = Set.of(BUY_CHANGED, CONTINUE_BUY);
    public static final Set<TrendSignal> SELL = Set.of(SELL_CHANGED, CONTINUE_SELL);

    private final String text;
    private final Color color;
}
