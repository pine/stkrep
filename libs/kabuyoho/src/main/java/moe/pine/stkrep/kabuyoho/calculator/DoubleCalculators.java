package moe.pine.stkrep.kabuyoho.calculator;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DoubleCalculators {
    public static final Calculator<Double> NONE = new NoneCalculator<>();
    public static final Calculator<Double> HUNDREDTH_DIVIDER = new DoubleDivider(100.0);
}
