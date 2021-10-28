package moe.pine.stkrep.kabuyoho.extractors;

public class SummaryBoxStringExtractor extends SummaryBoxExtractor<String> {
    public SummaryBoxStringExtractor(String title) {
        super(title);
    }

    @Override
    protected String onExtract(String text) {
        return text;
    }
}
