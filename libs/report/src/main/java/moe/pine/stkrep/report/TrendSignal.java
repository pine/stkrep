package moe.pine.stkrep.report;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TrendSignal implements TextEnum {
    BUY_CHANGED("買い転換"),
    CONTINUE_BUY("買い継続"),
    NEUTRAL("ニュートラル"),
    CONTINUE_SELL("売り継続"),
    SELL_CHANGED("売り転換"),
    ;

    private final String text;
}
