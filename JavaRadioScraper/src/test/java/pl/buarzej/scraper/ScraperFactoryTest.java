package pl.buarzej.scraper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ScraperFactoryTest {

    /*@Test
    public void testCreateRmfScraper() {
        BaseScraper scraper = ScraperFactory.getScraper("rmf");
        //TODO check if static import assert methods is better or worse whats the difference in this case
        Assertions.assertNotNull(scraper, "Scraper should not be null");
        Assertions.assertTrue(scraper instanceof RmfScraper, "Scraper should be an instance of RmfScraper");
    }

    @Test
    public void testUnsupportedStation() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            ScraperFactory.getScraper("unsupported");
        }, "No scraper configuration found for: unsupported");
    }*/
}
