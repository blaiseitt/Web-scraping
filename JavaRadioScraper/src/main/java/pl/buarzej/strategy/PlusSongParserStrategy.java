package pl.buarzej.strategy;

import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;
import pl.buarzej.model.StationDetails;
import pl.buarzej.model.Song;
import pl.buarzej.service.SongDateService;

@Component
public class PlusSongParserStrategy implements SongParserStrategy {

    private static final String CSS_TITLE = "div.vjsPlayingHistory__hit__title";
    private static final String CSS_AUTHOR = "div.vjsPlayingHistory__hit__author";
    private static final String CSS_PLAYDATE = "div.vjsPlayingHistory__hit__playdate";
    private static final String FILTER_HOUR_PHRASE = "Graliśmy o ";

    private final SongDateService songDateService;

    public PlusSongParserStrategy(SongDateService songDateService) {
        this.songDateService = songDateService;
    }
    @Override
    public Song parseSong(Element element, StationDetails stationDetails) {
        String title = element.select(CSS_TITLE).text();
        String author = element.select(CSS_AUTHOR).text();
        String hour = element.select(CSS_PLAYDATE).text();
        String filteredHour = hour.replace(FILTER_HOUR_PHRASE, "");
        String playedDate = songDateService.getSongPlayedDate(filteredHour);
        return new Song(title, author, filteredHour, playedDate, stationDetails.getDisplayName());
    }
}
