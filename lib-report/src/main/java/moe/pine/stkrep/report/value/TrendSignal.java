package moe.pine.stkrep.report.value;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import moe.pine.stkrep.report.color.Color;
import moe.pine.stkrep.report.color.ForegroundColor;

import java.util.Set;

/**
 * トレンドシグナル
 */
@Getter
@RequiredArgsConstructor
@SuppressFBWarnings("EI_EXPOSE_REP")
public enum TrendSignal implements TextEnum {
    BUY_CHANGED(Set.of("買い転換"), "買い転換", ForegroundColor.RED),
    CONTINUE_BUY(Set.of("買い継続"), "買い継続", ForegroundColor.RED),
    NEUTRAL(Set.of("ニュートラル"), "ニュートラル", ForegroundColor.BLACK),
    CONTINUE_SELL(Set.of("売り継続"), "売り継続", ForegroundColor.GREEN),
    SELL_CHANGED(Set.of("売り転換"), "売り転換", ForegroundColor.GREEN),
    NA(Set.of("--", ""), "", ForegroundColor.BLACK),
    ;

    public static final Set<TrendSignal> BUY = Set.of(BUY_CHANGED, CONTINUE_BUY);
    public static final Set<TrendSignal> SELL = Set.of(SELL_CHANGED, CONTINUE_SELL);

    private final Set<String> inputTexts;
    private final String outputText;
    private final Color color;
}
