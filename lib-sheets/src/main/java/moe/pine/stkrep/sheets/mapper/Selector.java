package moe.pine.stkrep.sheets.mapper;

@FunctionalInterface
public interface Selector<R, I> {
    I select(R report);
}
