package pl.buarzej.scraper;

import pl.buarzej.model.Song;

import java.util.List;

public interface BaseScraper {

    List<Song> scrapeSongs();
}
