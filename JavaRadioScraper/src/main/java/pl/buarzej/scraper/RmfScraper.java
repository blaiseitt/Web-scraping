package pl.buarzej.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import pl.buarzej.model.Song;

import java.util.ArrayList;
import java.util.List;

public class RmfScraper extends BaseScraper {

    @Override
    public List<Song> scrapeSongs() {

        String url = "https://live.rmf.fm/#utm_source=rmf.fm&utm_medium=menu";
        List<Song> songsList = new ArrayList<>();

        try {
            driver.get(url);
            Thread.sleep(2500);
            String pageSource = driver.getPageSource();

            Document document = Jsoup.parse(pageSource);
            Elements elements = document.select("div.item.song.visible");
            //need to scroll page in order to retrieve more songs
            System.out.println("Number of songs retrieved: " + elements.size());

            for (Element element: elements) {
                songsList.add(songFromElement(element));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }

        return songsList;
    }

    private static Song songFromElement(Element element) {
        String authorAndTitle = element.select("span.title-text").text();
        //split author and title - do it in more convinient way, for example artist a-ha is bugged because of this solution
        String authorAndTitleParts[] = authorAndTitle.split("-", 2);

        String hour = element.select("span.hour").text();
        Song song = new Song(authorAndTitleParts[1].trim(), authorAndTitleParts[0].trim(), hour, null, null);
        return song;
    }

}
