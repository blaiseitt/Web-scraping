package pl.buarzej.scraper;

import org.jsoup.nodes.Element;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pl.buarzej.model.Song;

import java.util.List;

public abstract class BaseScraper {

    protected WebDriver driver;

    public void initializeDriver() {
        this.driver = new ChromeDriver();
    }

    public abstract List<Song> scrapeSongs();

    public void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

}
