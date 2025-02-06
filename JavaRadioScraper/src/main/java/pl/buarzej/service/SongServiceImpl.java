package pl.buarzej.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.buarzej.dao.SongRepository;
import pl.buarzej.dao.StationDetailsRepository;
import pl.buarzej.model.Song;
import pl.buarzej.model.StationDetails;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
        LocalDateTime updateThreshold = LocalDateTime.now().minusMinutes(15).truncatedTo(ChronoUnit.SECONDS);

        List<Song> recentSongs = songRepository.findRecentSongs(stationName, updateThreshold);

        if (!recentSongs.isEmpty()) {
            System.out.println("Recent songs found in database.");
            return recentSongs;
        }

        List<Song> scrapedSongs = scrapingService.runSelectedScrapers(List.of(stationName));
        System.out.println("No recent songs found in database - they got scraped.");
        saveSongs(scrapedSongs);
        return scrapedSongs;
    }

    @Override
    public List<Song> getSongsForAll() {
        List<String> stationNames = stationDetailsRepository.findAllStationNames();
        List<Song> songs = scrapingService.runAllScrapers(stationNames);
        saveSongs(songs);
        return songs;
    }

    @Transactional
    private void saveSongs(List<Song> scrapedSongs) {
        if (scrapedSongs.isEmpty()) return;

        LocalDateTime batchTimestamp = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);

        for (Song song : scrapedSongs) {
            boolean exists = songRepository.existsByTitleAndAuthorAndPlayedHour(
                    song.getTitle(), song.getAuthor(), song.getPlayedHour()
            );

            if (!exists) {
                song.setLastUpdated(batchTimestamp);
                songRepository.save(song);
            }
        }
    }
}
