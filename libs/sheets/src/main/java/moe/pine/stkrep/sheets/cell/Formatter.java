package moe.pine.stkrep.sheets.cell;

@FunctionalInterface
public interface Formatter<R> {
    /**
     * Returns the default formatter
     */
    @SuppressWarnings("unchecked")
    static <R> Formatter<R> of() {
        return (Formatter<R>) DefaultFormatter.INSTANCE;
    }

    void run(R report, FormatBuilder builder);
}
