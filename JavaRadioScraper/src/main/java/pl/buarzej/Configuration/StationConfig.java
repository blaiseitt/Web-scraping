package pl.buarzej.Configuration;

public class StationConfig {
    private final String url;
    private final String displayName;

    public StationConfig(String url, String displayName) {
        this.url = url;
        this.displayName = displayName;
    }

    public String getUrl() {
        return url;
    }

    public String getDisplayName() {
        return displayName;
    }
}
