package pl.buarzej.strategy;

import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;
import pl.buarzej.model.StationDetails;
import pl.buarzej.model.Song;
import pl.buarzej.service.SongDateService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
        List<String> authorAndTitleParts = Optional.ofNullable(authorAndTitle)
                .map(text -> Arrays.asList(text.split("-", 2)))
                .filter(parts -> parts.size() == 2)
                .orElse(Arrays.asList("", ""));

        String hour = element.select(CSS_PLAYDATE).text();
        String playedDate = songDateService.getSongPlayedDate(hour);
        return new Song(authorAndTitleParts.get(1).trim(), authorAndTitleParts.get(0).trim(), hour, playedDate, stationDetails.getDisplayName());
    }
}
