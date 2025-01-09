package pl.buarzej.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.buarzej.Configuration.StationConfig;
import pl.buarzej.model.Song;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class RmfScraper extends BaseScraper {

    public static final String CSS_ELEMENTS = "div.item.song.visible";
    public static final String CSS_TITLE_AUTHOR = "span.title-text";
    public static final String CSS_PLAYDATE = "span.hour";

    public RmfScraper(StationConfig config) {
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
                Song song = songFromElement(element, config);
                songsList.add(song);
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
        String authorAndTitle = element.select(CSS_TITLE_AUTHOR).text();
        //split author and title - do it in more convinient way, for example artist a-ha is bugged because of this solution
        //when multiple authors they are split with '/'
        String authorAndTitleParts[] = authorAndTitle.split("-", 2);

        String hour = element.select(CSS_PLAYDATE).text();
        Song song = new Song(authorAndTitleParts[1].trim(), authorAndTitleParts[0].trim(), hour, null, config.getDisplayName());
        return song;
    }

}
