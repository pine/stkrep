package moe.pine.stkrep.kabuyoho.extractor;

import moe.pine.stkrep.kabuyoho.calculator.Calculator;
import moe.pine.stkrep.kabuyoho.calculator.DoubleCalculator;
import moe.pine.stkrep.kabuyoho.parser.DoubleParser;

@SuppressWarnings("PMD.BeanMembersShouldSerialize")
public class SummaryBoxDoubleExtractor extends AbstractSummaryBoxExtractor<Double> {
    private final Calculator<Double> calculator;

    public SummaryBoxDoubleExtractor(String title) {
        this(title, DoubleCalculator.NONE);
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
