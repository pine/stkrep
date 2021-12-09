package moe.pine.stkrep.kabuyoho.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DoubleCalculatorTest {
    @Test
    void noneTest() {
        assertEquals(Double.NaN, DoubleCalculator.NONE.calculate(Double.NaN), 0.0);
        assertEquals(0.0, DoubleCalculator.NONE.calculate(0.0), 0.0);
        assertEquals(0.5, DoubleCalculator.NONE.calculate(0.5), 0.0);
        assertEquals(1.0, DoubleCalculator.NONE.calculate(1.0), 0.0);
    }

    @Test
    void hundredthDividerTest() {
        assertEquals(Double.NaN, DoubleCalculator.HUNDREDTH_DIVIDER.calculate(Double.NaN), 0.0);
        assertEquals(0.0, DoubleCalculator.HUNDREDTH_DIVIDER.calculate(0.0), 0.0);
        assertEquals(0.5, DoubleCalculator.HUNDREDTH_DIVIDER.calculate(50.0), 0.0);
        assertEquals(1.0, DoubleCalculator.HUNDREDTH_DIVIDER.calculate(100.0), 0.0);
    }
}
