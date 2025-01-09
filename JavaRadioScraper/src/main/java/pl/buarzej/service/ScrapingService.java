package pl.buarzej.service;

import pl.buarzej.model.Song;
import pl.buarzej.scraper.BaseScraper;
import pl.buarzej.scraper.ScraperFactory;

import java.util.ArrayList;
import java.util.List;

public class ScrapingService {

    private final List<String> stationNames = List.of("rmf", "eska", "plus");

    public List<Song> runAllScrapers() {
        List<Song> songList = new ArrayList<>();
        stationNames.forEach(stationName -> {
            BaseScraper scraper = ScraperFactory.getScraper(stationName);
            scraper.initializeDriver();
            songList.addAll(scraper.scrapeSongs());
            scraper.closeDriver();
        });
        return songList;
    }

    public List<Song> runSelectedScrapers(List<String> selectedStations) {
        List<Song> songList = new ArrayList<>();
        selectedStations.forEach(stationName -> {
            BaseScraper scraper = ScraperFactory.getScraper(stationName);
            scraper.initializeDriver();
            songList.addAll(scraper.scrapeSongs());
            scraper.closeDriver();
        });
        return songList;
    }
}
