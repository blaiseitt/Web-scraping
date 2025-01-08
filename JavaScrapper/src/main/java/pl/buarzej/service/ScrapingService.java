package pl.buarzej.service;

import pl.buarzej.model.Song;
import pl.buarzej.scraper.BaseScraper;
import pl.buarzej.scraper.RmfScraper;
import pl.buarzej.scraper.ScraperFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScrapingService {

    public List<Song> runAllScrapers() {
        //TODO runAll and runSelected should be made in a same manner - string based probably
        List<BaseScraper> scrapers = Arrays.asList(new RmfScraper());
        List<Song> songList = new ArrayList<>();
        scrapers.forEach(scraper -> {
            scraper.initializeDriver();
            songList.addAll(scraper.scrapeSongs());
            scraper.closeDriver();
        });
        return songList;
    }

    public void runSelectedScrapers(List<String> stationNames) {
        for (String station : stationNames) {
            BaseScraper scraper = ScraperFactory.getScraper(station);
            scraper.initializeDriver();
            scraper.scrapeSongs();
            scraper.closeDriver();
        }
    }
}
