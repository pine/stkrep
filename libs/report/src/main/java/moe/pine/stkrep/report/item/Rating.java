package moe.pine.stkrep.report.item;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import moe.pine.stkrep.report.color.Color;
import moe.pine.stkrep.report.color.ForegroundColor;

import java.util.Set;

/**
 * レーティング
 */
@Getter
@RequiredArgsConstructor
@SuppressFBWarnings("EI_EXPOSE_REP")
public enum Rating implements TextEnum {
    STRONG_BUY(Set.of("強気"), "強気", ForegroundColor.RED),
    MODERATE_BUY(Set.of("やや強気"), "やや強気", ForegroundColor.RED),
    NEUTRAL(Set.of("中立"), "中立", ForegroundColor.BLACK),
    MODERATE_SELL(Set.of("やや弱気"), "やや弱気", ForegroundColor.GREEN),
    STRONG_SELL(Set.of("弱気"), "弱気", ForegroundColor.GREEN),
    NA(Set.of("--", ""), "", ForegroundColor.BLACK),
    ;

    public static final Set<Rating> BUY = Set.of(STRONG_BUY, MODERATE_BUY);
    public static final Set<Rating> SELL = Set.of(MODERATE_SELL, STRONG_SELL);

    private final Set<String> inputTexts;
    private final String outputText;
    private final Color color;
}
