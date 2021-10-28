package moe.pine.stkrep.kabuyoho.extractors;

import moe.pine.stkrep.kabuyoho.parsers.DoubleParser;

public class SummaryBoxDoubleExtractor extends SummaryBoxExtractor<Double> {
    public SummaryBoxDoubleExtractor(String title) {
        super(title);
    }

    @Override
    protected Double onExtract(String text) {
        return DoubleParser.parse(text);
    }
}
