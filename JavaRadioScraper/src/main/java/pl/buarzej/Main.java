package pl.buarzej;

import pl.buarzej.model.Song;
import pl.buarzej.service.ScrapingService;
import pl.buarzej.utils.PrintSongDataUtil;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "E:\\Projects\\Webdrivers\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        ScrapingService service = new ScrapingService();
        List<Song> songs = service.runAllScrapers();
        PrintSongDataUtil.printSongsDetails(songs);
    }

}
