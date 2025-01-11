package pl.buarzej.scraper;

import pl.buarzej.configuration.StationConfig;
import pl.buarzej.configuration.StationConfiguration;

public class ScraperFactory {

    public static BaseScraper getScraper(String stationName) {
        StationConfig config = StationConfiguration.getStationConfig(stationName);
        if (config == null) {
            throw new IllegalArgumentException("No scraper configuration found for: " + stationName);
        }
        return switch (stationName.toLowerCase()) {
            case "rmf" -> new RmfScraper(config);
            case "eska" -> new EskaScraper(config);
            case "plus" -> new PlusScraper(config);
            default -> throw new UnsupportedOperationException("Unsupported station: " + stationName);
        };
    }
}
