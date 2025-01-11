package pl.buarzej.strategy;

import org.jsoup.nodes.Element;
import pl.buarzej.configuration.StationConfig;
import pl.buarzej.model.Song;

public class EskaSongParserStrategy implements SongParserStrategy {

    private static final String CSS_TITLE = "div.vjsPlayingHistory__hit__title";
    private static final String CSS_AUTHOR = "div.vjsPlayingHistory__hit__author";
    private static final String CSS_PLAYDATE = "div.vjsPlayingHistory__hit__playdate";

    @Override
    public Song parseSong(Element element, StationConfig config) {
        String title = element.select(CSS_TITLE).text();
        String author = element.select(CSS_AUTHOR).text();
        //split author and title - do it in more convinient way, for example artist a-ha is bugged because of this solution
        //when multiple authors they are split with '/'

        //TODO filter "Graliśmy o "
        String hour = element.select(CSS_PLAYDATE).text();
        return new Song(title, author, hour, null, config.getDisplayName());
    }
}
