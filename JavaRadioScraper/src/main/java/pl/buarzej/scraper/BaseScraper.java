package pl.buarzej.scraper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pl.buarzej.configuration.StationConfig;
import pl.buarzej.model.Song;

import java.util.List;

public abstract class BaseScraper {

    protected WebDriver driver;
    protected final StationConfig config;

    public BaseScraper(StationConfig config) {
        this.config = config;
    }

    public abstract List<Song> scrapeSongs();

    public void initializeDriver() {
        this.driver = new ChromeDriver();
    }

    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

}
