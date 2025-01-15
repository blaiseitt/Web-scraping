package pl.buarzej.strategy;

import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;
import pl.buarzej.configuration.StationDetails;
import pl.buarzej.model.Song;

@Component
public class EskaSongParserStrategy implements SongParserStrategy {

    private static final String CSS_TITLE = "div.vjsPlayingHistory__hit__title";
    private static final String CSS_AUTHOR = "div.vjsPlayingHistory__hit__author";
    private static final String CSS_PLAYDATE = "div.vjsPlayingHistory__hit__playdate";
    private static final String FILTER_HOUR_PHRASE = "Graliśmy o ";

    @Override
    public Song parseSong(Element element, StationDetails stationDetails) {
        String title = element.select(CSS_TITLE).text();
        //TODO when multiple artists they are printed as list <ul><li> etc, do Artist model class and put them into separate instances
        String author = element.select(CSS_AUTHOR).text();
        //split author and title - do it in more convinient way, for example artist a-ha is bugged because of this solution
        //when multiple authors they are split with '/'

        //TODO think if this logic should be moved to separate method, util class or is it good here
        String hour = element.select(CSS_PLAYDATE).text();
        String filteredHour = hour.replace(FILTER_HOUR_PHRASE, "");
        //TODO service for playedDate
        return new Song(title, author, filteredHour, null, stationDetails.getDisplayName());
    }

}
