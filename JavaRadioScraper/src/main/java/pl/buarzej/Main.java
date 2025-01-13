package pl.buarzej;

import pl.buarzej.model.Song;
import pl.buarzej.service.ScrapingServiceImpl;
import pl.buarzej.utils.PrintSongDataUtil;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {

        Properties properties = new Properties();
        try (InputStream input = Main.class.getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(input);
        } catch (Exception e) {
            System.out.println("Unable to find specified properties file");
            e.printStackTrace();
            return;
        }

        System.setProperty(properties.getProperty("system.chromedriver"), properties.getProperty("path.to.chromedriver"));

        ScrapingServiceImpl service = new ScrapingServiceImpl();
        //TODO for linux plus on given url does not work
        List<Song> songs = service.runSelectedScrapers(List.of("plus"));
        System.out.print(PrintSongDataUtil.printSongsDetails(songs));
    }
}
