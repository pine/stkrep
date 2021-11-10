package moe.pine.stkrep.kabuyoho.extractor;

import moe.pine.stkrep.kabuyoho.parser.IntegerParser;

public class SummaryBoxIntegerExtractor extends AbstractSummaryBoxExtractor<Integer> {
    public SummaryBoxIntegerExtractor(String title) {
        super(title);
    }

    @Override
    protected Integer onExtract(String text) {
        return IntegerParser.parse(text);
    }
}
