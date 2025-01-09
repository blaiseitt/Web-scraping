package pl.buarzej.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.buarzej.Configuration.StationConfig;
import pl.buarzej.model.Song;

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
            Thread.sleep(2500);
            String pageSource = driver.getPageSource();

            Document document = Jsoup.parse(pageSource);
            Elements elements = document.select(CSS_ELEMENTS);
            //need to scroll page in order to retrieve more songs
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
        String authorAndTitle = element.select(CSS_TITLE_AUTHOR).text();
        //split author and title - do it in more convinient way, for example artist a-ha is bugged because of this solution
        //when multiple authors they are split with '/'
        String authorAndTitleParts[] = authorAndTitle.split("-", 2);

        String hour = element.select(CSS_PLAYDATE).text();
        Song song = new Song(authorAndTitleParts[1].trim(), authorAndTitleParts[0].trim(), hour, null, config.getDisplayName());
        return song;
    }

}
