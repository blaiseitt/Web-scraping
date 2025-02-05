package pl.buarzej.service;

import pl.buarzej.dao.SongRepository;
import pl.buarzej.model.Song;

import java.util.List;

public interface SongService {

    List<Song> getLatestSongs(String stationName);

    List<Song> getSongsForAll();
}
