package moe.pine.stkrep.report.item;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import moe.pine.stkrep.report.color.Color;
import moe.pine.stkrep.report.color.ForegroundColor;

import java.util.Set;

/**
 * 目標株価
 */
@Getter
@RequiredArgsConstructor
@SuppressFBWarnings("EI_EXPOSE_REP")
public enum StockPrice implements TextEnum {
    UNDERVALUED(Set.of("割安"), "割安", ForegroundColor.RED),
    SLIGHTLY_UNDERVALUED(Set.of("やや割安"), "やや割安", ForegroundColor.RED),
    REASONABLE(Set.of("妥当水準"), "妥当水準", ForegroundColor.RED),
    SLIGHTLY_OVERVALUED(Set.of("割高"), "やや割高", ForegroundColor.GREEN),
    OVERVALUED(Set.of("割高"), "割高", ForegroundColor.GREEN),
    NA(Set.of(""), "", ForegroundColor.BLACK),
    ;

    private final Set<String> inputTexts;
    private final String outputText;
    private final Color color;
}
