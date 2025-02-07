package pl.buarzej.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pl.buarzej.dao.StationDetailsRepository;
import pl.buarzej.model.Song;
import pl.buarzej.model.StationDetails;
import pl.buarzej.strategy.SongParserStrategy;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Component
public class PlusScraper implements BaseScraper {

    private static final String CSS_ELEMENTS = "div.vjsPlayingHistory__hit__info";

    private SongParserStrategy parser;
    private WebDriver driver;
    private StationDetailsRepository stationDetailsRepository;

    public PlusScraper(WebDriver driver,
                       @Qualifier("plusSongParserStrategy") SongParserStrategy parser,
                       StationDetailsRepository stationDetailsRepository) {
        this.driver = driver;
        this.parser = parser;
        this.stationDetailsRepository = stationDetailsRepository;
    }

    @Override
    public List<Song> scrapeSongs() {
        List<Song> songsList = new ArrayList<>();
        StationDetails stationDetails = stationDetailsRepository.findByName("plus");

        try {
            driver.get(stationDetails.getUrl());
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(CSS_ELEMENTS)));
            String pageSource = driver.getPageSource();

            Document document = Jsoup.parse(pageSource);
            Elements elements = document.select(CSS_ELEMENTS);
            System.out.println("Number of songs retrieved: " + elements.size());

            for (Element element: elements) {
                songsList.add(parser.parseSong(element, stationDetails));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return songsList;
    }
}
