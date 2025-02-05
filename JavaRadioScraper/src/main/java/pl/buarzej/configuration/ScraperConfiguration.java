package pl.buarzej.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.buarzej.scraper.BaseScraper;
import pl.buarzej.scraper.EskaScraper;
import pl.buarzej.scraper.PlusScraper;
import pl.buarzej.scraper.RmfScraper;

import java.util.Map;

@Configuration
public class ScraperConfiguration {

    //TODO more generic way of creating scraperMap
    @Bean
    public Map<String, BaseScraper> scraperMap(
            RmfScraper rmfScraper,
            EskaScraper eskaScraper,
            PlusScraper plusScraper
    ) {
        return Map.of(
                "rmffm", rmfScraper,
                "eska", eskaScraper,
                "plus", plusScraper
        );
    }
}
