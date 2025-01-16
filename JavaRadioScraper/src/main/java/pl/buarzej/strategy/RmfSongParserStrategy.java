package pl.buarzej.strategy;

import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;
import pl.buarzej.configuration.StationDetails;
import pl.buarzej.model.Song;
import pl.buarzej.service.SongDateService;

@Component
public class RmfSongParserStrategy implements SongParserStrategy {

    private static final String CSS_TITLE_AUTHOR = "span.title-text";
    private static final String CSS_PLAYDATE = "span.hour";

    private final SongDateService songDateService;

    public RmfSongParserStrategy(SongDateService songDateService) {
        this.songDateService = songDateService;
    }
    @Override
    public Song parseSong(Element element, StationDetails stationDetails) {
        String authorAndTitle = element.select(CSS_TITLE_AUTHOR).text();
        //split author and title - do it in more convinient way, for example artist a-ha is bugged because of this solution
        //when multiple authors they are split with '/'
        String authorAndTitleParts[] = authorAndTitle.split("-", 2);

        String hour = element.select(CSS_PLAYDATE).text();
        String playedDate = songDateService.getSongPlayedDate(hour);
        //TODO wtf is this fix for sure
        return new Song(authorAndTitleParts[1].trim(), authorAndTitleParts[0].trim(), hour, playedDate, stationDetails.getDisplayName());
    }
}
