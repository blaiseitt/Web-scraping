package pl.buarzej.controller;

import org.springframework.web.bind.annotation.*;
import pl.buarzej.model.Song;
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
        List<Song> songs = songService.getLatestSongs(station);
        return populateRadioSongs(songs);
    }

    @GetMapping("/all-stations")
    public Map<String, List<Map<String, String>>> getAllSongs() {
        List<Song> songs = songService.getSongsForAll();
        return populateRadioSongs(songs);
    }

    private Map<String, List<Map<String, String>>> populateRadioSongs(List<Song> songs) {
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
