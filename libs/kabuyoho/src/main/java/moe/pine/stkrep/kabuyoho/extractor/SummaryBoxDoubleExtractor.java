package moe.pine.stkrep.kabuyoho.extractor;

import moe.pine.stkrep.kabuyoho.calculator.Calculator;
import moe.pine.stkrep.kabuyoho.calculator.DoubleCalculators;
import moe.pine.stkrep.kabuyoho.parser.DoubleParser;

public class SummaryBoxDoubleExtractor extends SummaryBoxExtractor<Double> {
    private final Calculator<Double> calculator;

    public SummaryBoxDoubleExtractor(String title) {
        this(title, DoubleCalculators.NONE);
    }

    public SummaryBoxDoubleExtractor(String title, Calculator<Double> calculator) {
        super(title);
        this.calculator = calculator;
    }

    @Override
    protected Double onExtract(String text) {
        return calculator.calculate(DoubleParser.parse(text));
    }
}
