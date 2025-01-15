package pl.buarzej.scraper;

import org.openqa.selenium.WebDriver;
import pl.buarzej.model.Song;
import pl.buarzej.strategy.SongParserStrategy;

import java.util.List;

public abstract class BaseScraper {

    protected final WebDriver driver;
    protected final SongParserStrategy parser;

    public BaseScraper(WebDriver driver,
                       SongParserStrategy parser) {
        this.driver = driver;
        this.parser = parser;
    }

    public abstract List<Song> scrapeSongs();

    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

}
