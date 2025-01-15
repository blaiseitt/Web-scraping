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

    @Bean
    public Map<String, BaseScraper> scraperMap(
            RmfScraper rmfScraper,
            EskaScraper eskaScraper,
            PlusScraper plusScraper
    ) {
        return Map.of(
                "rmf", rmfScraper,
                "eska", eskaScraper,
                "plus", plusScraper
        );
    }
}
