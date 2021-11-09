package moe.pine.stkrep.report.text;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import moe.pine.stkrep.report.color.Color;
import moe.pine.stkrep.report.color.ForegroundColor;

@Getter
@RequiredArgsConstructor
public enum RiskOn implements TextEnum {
    BOTTOM_PRICE_ZONE("底値圏突入", ForegroundColor.RED),
    NEUTRAL("--", ForegroundColor.BLACK),
    HIGH_PRICE_ZONE("高値圏警戒", ForegroundColor.GREEN),
    ;

    private final String text;
    private final Color color;
}
