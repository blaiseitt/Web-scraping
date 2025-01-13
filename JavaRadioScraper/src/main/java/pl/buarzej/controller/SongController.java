package pl.buarzej.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.buarzej.model.Song;
import pl.buarzej.service.ScrapingServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SongController {

    private final ScrapingServiceImpl scrapingService;

    public SongController(ScrapingServiceImpl scrapingService) {
        this.scrapingService = scrapingService;
    }

    @GetMapping("/{station}")
    public List<Song> getSongsByStation(@PathVariable String station) {
        //TODO runSingleScraper add and use here
        return scrapingService.runSelectedScrapers(List.of(station));
    }

    @GetMapping("/all-stations")
    public List<Song> getAllSongs() {
        return scrapingService.runAllScrapers();
    }
}
