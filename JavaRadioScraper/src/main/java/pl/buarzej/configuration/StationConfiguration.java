package pl.buarzej.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import pl.buarzej.model.StationDetails;

import java.util.Map;

@Configuration
@PropertySource("classpath:scrapers.properties")
public class StationConfiguration {
//TODO i dont like this solution
    @Value("${rmf.url}")
    private String rmfUrl;

    @Value("${rmf.displayName}")
    private String rmfDisplayName;

    @Value("${eska.url}")
    private String eskaUrl;

    @Value("${eska.displayName}")
    private String eskaDisplayName;

    @Value("${plus.url}")
    private String plusUrl;

    @Value("${plus.displayName}")
    private String plusDisplayName;

    @Bean
    public Map<String, StationDetails> stationDetailsMap() {
        return Map.of(
                "rmf", new StationDetails(rmfUrl, rmfDisplayName),
                "eska", new StationDetails(eskaUrl, eskaDisplayName),
                "plus", new StationDetails(plusUrl, plusDisplayName)
        );
    }
}
