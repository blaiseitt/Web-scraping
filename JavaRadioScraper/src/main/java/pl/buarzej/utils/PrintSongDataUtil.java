package pl.buarzej.utils;

import pl.buarzej.model.Song;

import java.util.List;

public final class PrintSongDataUtil {

    private PrintSongDataUtil() {}

    public static void printSongsDetails(List<Song> songs) {
        for (Song song : songs) {
            System.out.println("Song title: " + song.getTitle() + ", artist: " + song.getAuthor() + ", played at "
                    + song.getPlayedTime() + " in station: " + song.getStationName() + ".");
        }
    }
}
