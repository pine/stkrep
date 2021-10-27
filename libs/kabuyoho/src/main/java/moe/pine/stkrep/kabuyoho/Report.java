package moe.pine.stkrep.kabuyoho;

import moe.pine.stkrep.format.FormattedText;
import org.springframework.lang.Nullable;

public record Report(
        int code,
        String uri,
        String name,
        String price,
        FormattedText signal,
        FormattedText level,
        FormattedText rating,
        FormattedText forecastByAnalyst,
        FormattedText forecastByPbr,
        FormattedText forecastByPer,
        String per,
        String pbr,
        String dividendYield
) {
}
