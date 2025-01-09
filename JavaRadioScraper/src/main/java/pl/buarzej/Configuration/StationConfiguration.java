package pl.buarzej.Configuration;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map;

public class StationConfiguration {

    private static final Map<String, StationConfig> STATION_CONFIGS = Map.ofEntries(
                new SimpleEntry<>("rmf", new StationConfig("https://live.rmf.fm/", "RMF FM")),
                new SimpleEntry<>("eska", new StationConfig("https://www.eska.pl/co-bylo-grane/", "ESKA")));

    public static StationConfig getStationConfig(String stationName) {
        return STATION_CONFIGS.get(stationName);
    }
}
