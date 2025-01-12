package pl.buarzej.strategy;

import org.jsoup.nodes.Element;
import pl.buarzej.configuration.StationConfig;
import pl.buarzej.model.Song;

public class PlusSongParserStrategy implements SongParserStrategy {

    private static final String CSS_TITLE = "div.vjsPlayingHistory__hit__title";
    private static final String CSS_AUTHOR = "div.vjsPlayingHistory__hit__author";
    private static final String CSS_PLAYDATE = "div.vjsPlayingHistory__hit__playdate";
    private static final String FILTER_HOUR_PHRASE = "Graliśmy o ";

    @Override
    public Song parseSong(Element element, StationConfig config) {
        String title = element.select(CSS_TITLE).text();
        String author = element.select(CSS_AUTHOR).text();
        //TODO Same as in Eska
        String hour = element.select(CSS_PLAYDATE).text();
        String filteredHour = hour.replace(FILTER_HOUR_PHRASE, "");
        return new Song(title, author, filteredHour, null, config.getDisplayName());
    }
}
