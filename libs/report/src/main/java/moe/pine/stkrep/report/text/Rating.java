package moe.pine.stkrep.report.text;

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
public enum Rating implements TextEnum {
    STRONG_BUY(Set.of("強気"), "強気", ForegroundColor.RED),
    MODERATE_BUY(Set.of("やや強気"), "やや強気", ForegroundColor.RED),
    NEUTRAL(Set.of("中立", ""), "中立", ForegroundColor.BLACK),
    MODERATE_SELL(Set.of("やや弱気"), "やや弱気", ForegroundColor.GREEN),
    STRONG_SELL(Set.of("弱気"), "弱気", ForegroundColor.GREEN),
    ;

    private final Set<String> inputTexts;
    private final String outputText;
    private final Color color;
}
