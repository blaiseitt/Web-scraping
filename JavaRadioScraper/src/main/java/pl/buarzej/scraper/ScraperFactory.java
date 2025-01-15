package pl.buarzej.scraper;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ScraperFactory {

    private final Map<String, BaseScraper> scraperMap;

    public ScraperFactory(Map<String, BaseScraper> scraperMap) {
        this.scraperMap = scraperMap;
    }

    public BaseScraper getScraper(String stationName) {
        BaseScraper scraper = scraperMap.get(stationName.toLowerCase());
        if (scraper == null) {
            throw new UnsupportedOperationException("Unsupported station: " + stationName);
        }
        return scraper;
    }
}
