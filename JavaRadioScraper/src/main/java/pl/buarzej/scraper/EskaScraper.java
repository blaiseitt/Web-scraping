package pl.buarzej.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.buarzej.Configuration.StationConfig;
import pl.buarzej.model.Song;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class EskaScraper extends BaseScraper {

    public static final String CSS_ELEMENTS = "div.vjsPlayingHistory__hit__info";
    public static final String CSS_TITLE = "div.vjsPlayingHistory__hit__title";
    public static final String CSS_AUTHOR = "div.vjsPlayingHistory__hit__author";
    public static final String CSS_PLAYDATE = "div.vjsPlayingHistory__hit__playdate";


    public EskaScraper(StationConfig config) {
        super(config);
    }

    @Override
    public List<Song> scrapeSongs() {

        String url = config.getUrl();
        List<Song> songsList = new ArrayList<>();

        try {
            driver.get(url);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(CSS_ELEMENTS)));
            String pageSource = driver.getPageSource();

            Document document = Jsoup.parse(pageSource);
            Elements elements = document.select(CSS_ELEMENTS);
            System.out.println("Number of songs retrieved: " + elements.size());

            for (Element element: elements) {
                songsList.add(songFromElement(element, config));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }

        return songsList;
    }

    //TODO separate class so it is easier for testing
    private static Song songFromElement(Element element, StationConfig config) {
        String title = element.select(CSS_TITLE).text();
        String author = element.select(CSS_AUTHOR).text();
        //split author and title - do it in more convinient way, for example artist a-ha is bugged because of this solution
        //when multiple authors they are split with '/'

        //TODO filter "Graliśmy o "
        String hour = element.select(CSS_PLAYDATE).text();
        Song song = new Song(title, author, hour, null, config.getDisplayName());
        return song;
    }
}
