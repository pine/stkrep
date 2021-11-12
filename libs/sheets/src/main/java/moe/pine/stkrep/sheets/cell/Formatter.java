package moe.pine.stkrep.sheets.cell;

@FunctionalInterface
public interface Formatter<R> {
    void run(R report, FormatBuilder builder);
}
