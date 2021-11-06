package moe.pine.stkrep.kabuyoho.calculator;

public enum DoubleCalculator implements Calculator<Double> {
    NONE {
        @Override
        double onCalculate(double value) {
            return value;
        }
    },
    HUNDREDTH_DIVIDER {
        @Override
        double onCalculate(double value) {
            return value / 100.0;
        }
    },
    ;

    @Override
    public Double calculate(Double value) {
        if (Double.isNaN(value)) {
            return value;
        }

        return onCalculate(value);
    }

    abstract double onCalculate(double value);
}
