package moe.pine.stkrep.report.value;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import moe.pine.stkrep.report.color.Color;
import moe.pine.stkrep.report.color.ForegroundColor;

import java.util.Set;

/**
 * リスクオン相対指数
 */
@Getter
@RequiredArgsConstructor
@SuppressFBWarnings("EI_EXPOSE_REP")
public enum RiskOn implements TextEnum {
    BOTTOM_PRICE_ZONE(Set.of("底値圏突入"), "底値圏突入", ForegroundColor.RED),
    HIGH_PRICE_ZONE(Set.of("高値圏警戒"), "高値圏警戒", ForegroundColor.GREEN),
    NA(Set.of("--", ""), "", ForegroundColor.BLACK),
    ;

    private final Set<String> inputTexts;
    private final String outputText;
    private final Color color;
}
