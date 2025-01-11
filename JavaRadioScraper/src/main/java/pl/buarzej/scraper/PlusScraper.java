package pl.buarzej.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.buarzej.configuration.StationConfig;
import pl.buarzej.model.Song;
import pl.buarzej.strategy.SongParserStrategy;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class PlusScraper extends BaseScraper {

    private static final String CSS_ELEMENTS = "div.vjsPlayingHistory__hit__info";

    public PlusScraper(StationConfig config) {
        super(config);
    }

    @Override
    public List<Song> scrapeSongs() {
        String url = config.getUrl();
        List<Song> songsList = new ArrayList<>();
        SongParserStrategy parser = config.getSongParserStrategy();

        try {
            driver.get(url);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(CSS_ELEMENTS)));
            String pageSource = driver.getPageSource();

            Document document = Jsoup.parse(pageSource);
            Elements elements = document.select(CSS_ELEMENTS);
            System.out.println("Number of songs retrieved: " + elements.size());

            for (Element element: elements) {
                songsList.add(parser.parseSong(element, config));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }

        return songsList;
    }
}
