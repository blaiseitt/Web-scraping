package pl.buarzej.configuration;

import pl.buarzej.strategy.SongParserStrategy;

public class StationConfig {
    private final String url;
    private final String displayName;
    private final SongParserStrategy songParserStrategy;

    public StationConfig(String url, String displayName, SongParserStrategy songParserStrategy) {
        this.url = url;
        this.displayName = displayName;
        this.songParserStrategy = songParserStrategy;
    }

    public String getUrl() {
        return url;
    }

    public String getDisplayName() {
        return displayName;
    }

    public SongParserStrategy getSongParserStrategy() {
        return songParserStrategy;
    }
}
