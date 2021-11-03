package moe.pine.stkrep.kabuyoho.calculator;

public class NoneCalculator<T extends Number> implements Calculator<T> {
    @Override
    public T calculate(T value) {
        return value;
    }
}
