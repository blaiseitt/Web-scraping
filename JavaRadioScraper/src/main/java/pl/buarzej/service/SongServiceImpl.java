package pl.buarzej.service;

import org.springframework.stereotype.Service;
import pl.buarzej.dao.SongRepository;
import pl.buarzej.dao.StationDetailsRepository;
import pl.buarzej.model.Song;
import pl.buarzej.model.StationDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SongServiceImpl implements SongService {

    private SongRepository songRepository;
    private ScrapingService scrapingService;
    private StationDetailsRepository stationDetailsRepository;

    public SongServiceImpl(SongRepository songRepository,
                           ScrapingService scrapingService,
                           StationDetailsRepository stationDetailsRepository) {
        this.songRepository = songRepository;
        this.scrapingService = scrapingService;
        this.stationDetailsRepository = stationDetailsRepository;
    }

    @Override
    public List<Song> getLatestSongs(String stationName) {
        //TODO prevent duplicate songs from appearing
        LocalDateTime updateThreshold = LocalDateTime.now().minusMinutes(15);

        List<Song> recentSongs = songRepository.findRecentSongs(stationName, updateThreshold);

        if (!recentSongs.isEmpty()) {
            System.out.println("Recent songs found in database.");
            return recentSongs;
        }

        List<Song> scrapedSongs = scrapingService.runSelectedScrapers(List.of(stationName));
        System.out.println("No recent songs found in database - they got scraped.");
        songRepository.saveAll(scrapedSongs);
        return scrapedSongs;
    }

    @Override
    public List<Song> getSongsForAll() {
        List<String> stationNames = stationDetailsRepository.findAllStationNames();
        List<Song> songs = scrapingService.runAllScrapers(stationNames);
        songRepository.saveAll(songs);
        return songs;
    }
}
