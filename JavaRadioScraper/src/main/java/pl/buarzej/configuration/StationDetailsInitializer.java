package pl.buarzej.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import pl.buarzej.dao.StationDetailsRepository;
import pl.buarzej.model.StationDetails;

import java.util.List;

@Component
@PropertySource("classpath:scrapers.properties")
public class StationDetailsInitializer implements CommandLineRunner {

    private final StationDetailsRepository stationDetailsRepository;

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

    public StationDetailsInitializer(StationDetailsRepository stationDetailsRepository) {
        this.stationDetailsRepository = stationDetailsRepository;
    }

    @Override
    public void run(String... args) {
        if (stationDetailsRepository.count() == 0) {
            List<StationDetails> stations = List.of(
                    new StationDetails("rmffm", rmfUrl, rmfDisplayName),
                    new StationDetails("eska", eskaUrl, eskaDisplayName),
                    new StationDetails("plus", plusUrl, plusDisplayName)
            );
            stationDetailsRepository.saveAll(stations);
            System.out.println("StationDetails were initialized.");
        } else {
            System.out.println("StationDetails already exists, initialization skipped.");
        }

    }
}
