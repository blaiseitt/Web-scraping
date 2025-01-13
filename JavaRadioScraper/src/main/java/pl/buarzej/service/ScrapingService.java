package pl.buarzej.service;

import pl.buarzej.model.Song;

import java.util.List;

public interface ScrapingService {

    List<Song> runAllScrapers();

    List<Song> runSelectedScrapers(List<String> stations);
}
