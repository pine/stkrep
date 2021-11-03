package moe.pine.stkrep.kabuyoho.calculator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DoubleDivider implements Calculator<Double> {
    private final double divisor;

    @Override
    public Double calculate(Double value) {
        if (Double.isNaN(value)) {
            return Double.NaN;
        }

        return value / divisor;
    }
}
