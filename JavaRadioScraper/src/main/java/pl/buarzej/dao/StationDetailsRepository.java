package pl.buarzej.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.buarzej.model.Song;
import pl.buarzej.model.StationDetails;

import java.util.List;

@Repository
@Transactional
public interface StationDetailsRepository extends JpaRepository<StationDetails, Long> {

    StationDetails findByName(String name);

    @Query("SELECT s.name FROM StationDetails s")
    List<String> findAllStationNames();
}
