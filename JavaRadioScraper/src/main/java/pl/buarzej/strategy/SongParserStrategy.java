package pl.buarzej.strategy;

import org.jsoup.nodes.Element;
import pl.buarzej.configuration.StationConfig;
import pl.buarzej.model.Song;

public interface SongParserStrategy {
    Song parseSong(Element element, StationConfig config);
}
