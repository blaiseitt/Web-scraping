package pl.buarzej.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.buarzej.model.Song;
import pl.buarzej.utils.PrintSongDataUtil;

import java.util.List;

public class PrintSongDataUtilTest {

    @Test
    public void testPrintSongsDetails() {
        List<Song> songs = List.of(
                new Song("Je veux", "Zaz", "12:35", null, "Plus Radio"),
                new Song("UNDONE", "Noisekick", "00:19", null, "RMF FM")
        );//TODO

        String result = PrintSongDataUtil.printSongsDetails(songs);
        String expectedOutput = "Song title: Je veux, artist: Zaz, played at 12:35 in station: Plus Radio.\n" +
                "Song title: UNDONE, artist: Noisekick, played at 00:19 in station: RMF FM.\n";

        Assertions.assertEquals(expectedOutput, result);
    }

    //figure out more test cases
}
