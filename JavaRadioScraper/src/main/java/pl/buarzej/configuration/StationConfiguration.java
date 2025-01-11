package pl.buarzej.configuration;

import pl.buarzej.strategy.EskaSongParserStrategy;
import pl.buarzej.strategy.PlusSongParserStrategy;
import pl.buarzej.strategy.RmfSongParserStrategy;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map;

public class StationConfiguration {

    private static final Map<String, StationConfig> STATION_CONFIGS = Map.ofEntries(
                new SimpleEntry<>("rmf", new StationConfig("https://live.rmf.fm/", "RMF FM", new RmfSongParserStrategy())),
                new SimpleEntry<>("eska", new StationConfig("https://www.eska.pl/co-bylo-grane/", "ESKA", new EskaSongParserStrategy())),
                new SimpleEntry<>("plus", new StationConfig("https://www.radioplus.pl/co-bylo-grane/", "Radio Plus", new PlusSongParserStrategy())));

    public static StationConfig getStationConfig(String stationName) {
        return STATION_CONFIGS.get(stationName);
    }
}
