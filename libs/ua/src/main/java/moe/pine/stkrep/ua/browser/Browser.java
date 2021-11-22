package moe.pine.stkrep.ua.browser;

@FunctionalInterface
public interface Browser {
    static Browser of() {
        return DefaultBrowser.INSTANCE;
    }

    BrowsingResults browse();
}
