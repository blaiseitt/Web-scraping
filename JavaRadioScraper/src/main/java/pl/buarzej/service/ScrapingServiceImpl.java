package pl.buarzej.service;

import org.springframework.stereotype.Service;
import pl.buarzej.model.Song;
import pl.buarzej.scraper.BaseScraper;
import pl.buarzej.scraper.ScraperFactory;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScrapingServiceImpl implements ScrapingService{

    private final List<String> stationNames = List.of("rmf", "eska", "plus");
    private final ScraperFactory scraperFactory;

    public ScrapingServiceImpl(ScraperFactory scraperFactory) {
        this.scraperFactory = scraperFactory;
    }

    public List<Song> runAllScrapers() {
        List<Song> songList = new ArrayList<>();
        stationNames.forEach(stationName -> {
            BaseScraper scraper = scraperFactory.getScraper(stationName);
            songList.addAll(scraper.scrapeSongs());
        });
        return songList;
    }

    public List<Song> runSelectedScrapers(List<String> selectedStations) {
        List<Song> songList = new ArrayList<>();
        selectedStations.forEach(stationName -> {
            BaseScraper scraper = scraperFactory.getScraper(stationName);
            songList.addAll(scraper.scrapeSongs());
        });
        return songList;
    }
}
