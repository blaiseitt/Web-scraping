package pl.buarzej;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SongScrapper {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "E:\\Projects\\Webdrivers\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String dynamicContentUrl = "https://live.rmf.fm/#utm_source=rmf.fm&utm_medium=menu";

        try {
            driver.get(dynamicContentUrl);
            Thread.sleep(2500);
            String pageSource = driver.getPageSource();

            Document document = Jsoup.parse(pageSource);
            Elements songs = document.select("div.item.song.visible");
            //need to scroll page in order to retrieve more songs
            System.out.println("Number of songs retrieved: " + songs.size());

            for (Element song: songs) {
                printSongDetails(song);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

    private static void printSongDetails(Element song) {
        String authorAndTitle = song.select("span.title-text").text();
        //split author and title - do it in more convinient way, for example artist a-ha is bugged because of this solution
        String authorAndTitleParts[] = authorAndTitle.split("-", 2);

        String hour = song.select("span.hour").text();
        System.out.println("Artist: " + authorAndTitleParts[0].trim());
        System.out.println("Song title: " + authorAndTitleParts[1].trim());
        System.out.println("Time played: " + hour);
    }
}
