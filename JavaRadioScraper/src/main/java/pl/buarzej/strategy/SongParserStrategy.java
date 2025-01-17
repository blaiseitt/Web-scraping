package pl.buarzej.strategy;

import org.jsoup.nodes.Element;
import pl.buarzej.model.StationDetails;
import pl.buarzej.model.Song;

public interface SongParserStrategy {
    Song parseSong(Element element, StationDetails stationDetails);
}
