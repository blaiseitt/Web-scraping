package pl.buarzej.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.buarzej.dao.StationDetailsRepository;
import pl.buarzej.model.StationDetails;
import pl.buarzej.scraper.BaseScraper;
import pl.buarzej.scraper.EskaScraper;
import pl.buarzej.scraper.PlusScraper;
import pl.buarzej.scraper.RmfScraper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class ScraperConfiguration {

    private final StationDetailsRepository stationDetailsRepository;
    private final Map<String, BaseScraper> availableScrapers;

    public ScraperConfiguration(StationDetailsRepository stationDetailsRepository, List<BaseScraper> scrapers) {
        this.stationDetailsRepository = stationDetailsRepository;
        this.availableScrapers = scrapers.stream()
                .collect(Collectors.toMap(scraper -> scraper.getClass().getSimpleName().replace("Scraper", "").toLowerCase(), Function.identity()));
    }

    @Bean
    public Map<String, BaseScraper> scraperMap() {
        Map<String, BaseScraper> scraperMap = new HashMap<>();
        List<StationDetails> stations = stationDetailsRepository.findAll();

        for (StationDetails station : stations) {
            String stationKey = station.getName().toLowerCase();
            BaseScraper scraper = availableScrapers.get(stationKey);

            if (scraper != null) {
                scraperMap.put(stationKey, scraper);
            }
        }

        return scraperMap;
    }
}
