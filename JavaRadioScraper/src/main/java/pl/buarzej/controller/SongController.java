package pl.buarzej.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.buarzej.model.Song;
import pl.buarzej.service.ScrapingService;
import pl.buarzej.service.ScrapingServiceImpl;
import pl.buarzej.service.SongService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SongController {

    private SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping("/{station}")
    public Map<String, List<Map<String, String>>> getSongsByStation(@PathVariable String station) {
        //TODO runSingleScraper(stationName) add and use here
        List<Song> songs = songService.getLatestSongs(station);

        //TODO do it in separate function (populator) + DTO
        return songs.stream()
                .collect(Collectors.groupingBy(
                        song -> song.getStationDetails().getDisplayName(),
                        Collectors.mapping(song -> {
                            Map<String, String> songData = new HashMap<>();
                            songData.put("title", song.getTitle());
                            songData.put("author", song.getAuthor());
                            songData.put("playedHour", song.getPlayedHour());
                            songData.put("playedDate", song.getPlayedDate());
                            return songData;
                        }, Collectors.toList())
                ));
    }

    @GetMapping("/all-stations")
    public Map<String, List<Map<String, String>>> getAllSongs() {
        List<Song> songs = songService.getSongsForAll();
        return songs.stream()
                .collect(Collectors.groupingBy(
                        song -> song.getStationDetails().getDisplayName(),
                        Collectors.mapping(song -> {
                            Map<String, String> songData = new HashMap<>();
                            songData.put("title", song.getTitle());
                            songData.put("author", song.getAuthor());
                            songData.put("playedHour", song.getPlayedHour());
                            songData.put("playedDate", song.getPlayedDate());
                            return songData;
                        }, Collectors.toList())
                ));
    }
}
