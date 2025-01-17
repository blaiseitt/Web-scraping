package pl.buarzej.model;

public class StationDetails {
    private final String url;
    private final String displayName;

    public StationDetails(String url, String displayName) {
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
