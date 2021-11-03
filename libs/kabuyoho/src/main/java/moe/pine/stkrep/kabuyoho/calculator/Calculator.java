package moe.pine.stkrep.kabuyoho.calculator;

public interface Calculator<T extends Number> {
    T calculate(T value);
}
