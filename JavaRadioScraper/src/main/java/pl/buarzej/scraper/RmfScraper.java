package pl.buarzej.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
public class RmfScraper implements BaseScraper {

    private static final String CSS_ELEMENTS = "div.item.song.visible";
    private SongParserStrategy parser;
    private WebDriver driver;
    private StationDetailsRepository stationDetailsRepository;

    public RmfScraper(WebDriver driver,
                      @Qualifier("rmfSongParserStrategy") SongParserStrategy parser,
                      StationDetailsRepository stationDetailsRepository) {
        this.driver = driver;
        this.parser = parser;
        this.stationDetailsRepository = stationDetailsRepository;
    }

    @Override
    public List<Song> scrapeSongs() {

        List<Song> songsList = new ArrayList<>();
        StationDetails stationDetails = stationDetailsRepository.findByName("rmffm");

        try {
            driver.get(stationDetails.getUrl());
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(CSS_ELEMENTS)));

            //TODO read about scrolls - why can i see songs on page after 3 scrolls but 7 are necessary?
            int scrollAttempts = 0;
            final int maxScrollAttempts = 7;
            final int scrollPauseTime = 250;
            final int demandedSize = 12;
            Elements elements = null;

            while (scrollAttempts < maxScrollAttempts) {
                String pageSource = driver.getPageSource();
                Document document = Jsoup.parse(pageSource);
                elements = document.select(CSS_ELEMENTS);
                System.out.println("Number of songs retrieved after scroll " + scrollAttempts + ": " + elements.size());

                if (elements.size() >= demandedSize) {
                    break;
                }

                ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 1600)");
                //TODO figure out condition for wait.until so it can be used instead thread.sleep
                Thread.sleep(scrollPauseTime);
                scrollAttempts++;
            }
            for (Element element : elements) {
                songsList.add(parser.parseSong(element, stationDetails));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return songsList;
    }

}
