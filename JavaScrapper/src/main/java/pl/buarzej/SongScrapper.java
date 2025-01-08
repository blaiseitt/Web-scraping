package pl.buarzej;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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
            String authorAndTitle = songs.first().select("span.title-text").text();

            //split author and title - do it in more convinient way
            String authorAndTitleParts[] = authorAndTitle.split("-", 2);

            String hour = songs.first().select("span.hour").text();
            System.out.println("Artist: " + authorAndTitleParts[0].trim());
            System.out.println("Song title: " + authorAndTitleParts[1].trim());
            System.out.println("Time played: " + hour);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }

    }
}
