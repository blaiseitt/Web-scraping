package pl.buarzej.scraper;

public class ScraperFactory {

    public static BaseScraper getScraper(String station) {
        switch (station.toLowerCase()) {
            case "rmf":
                return new RmfScraper();
            default:
                throw new IllegalArgumentException("Unknown station: " + station);
        }
    }
}
