package pl.buarzej.strategy;

import org.jsoup.nodes.Element;
import pl.buarzej.configuration.StationConfig;
import pl.buarzej.model.Song;

public class RmfSongParserStrategy implements SongParserStrategy {

    private static final String CSS_TITLE_AUTHOR = "span.title-text";
    private static final String CSS_PLAYDATE = "span.hour";

    @Override
    public Song parseSong(Element element, StationConfig config) {
        String authorAndTitle = element.select(CSS_TITLE_AUTHOR).text();
        //split author and title - do it in more convinient way, for example artist a-ha is bugged because of this solution
        //when multiple authors they are split with '/'
        String authorAndTitleParts[] = authorAndTitle.split("-", 2);

        String hour = element.select(CSS_PLAYDATE).text();
        return new Song(authorAndTitleParts[1].trim(), authorAndTitleParts[0].trim(), hour, null, config.getDisplayName());
    }
}
