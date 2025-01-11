package pl.buarzej.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.buarzej.configuration.StationConfig;
import pl.buarzej.model.Song;
import pl.buarzej.strategy.SongParserStrategy;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class RmfScraper extends BaseScraper {

    private static final String CSS_ELEMENTS = "div.item.song.visible";

    public RmfScraper(StationConfig config) {
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
            //TODO is it possible for browser to not popup?
            for (Element element : elements) {
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
