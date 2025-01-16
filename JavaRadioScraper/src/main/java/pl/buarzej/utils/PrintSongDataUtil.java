package pl.buarzej.utils;

import pl.buarzej.model.Song;

import java.util.List;

public final class PrintSongDataUtil {

    private PrintSongDataUtil() {}

    public static String printSongsDetails(List<Song> songs) {
        StringBuilder result = new StringBuilder();
        for (Song song : songs) {
            result.append("Song title: ").append(song.getTitle())
                    .append(", artist: ").append(song.getAuthor())
                    .append(", played at ").append(song.getPlayedHour())
                    .append(" in station: ").append(song.getStationName()).append(".\n");
        }
        return result.toString();
    }
}
