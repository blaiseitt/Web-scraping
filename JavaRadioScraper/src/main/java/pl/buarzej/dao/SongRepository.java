package pl.buarzej.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.buarzej.model.Song;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional
public interface SongRepository extends JpaRepository<Song, Long> {

    @Query("SELECT s FROM Song s WHERE s.stationDetails.name = :stationName " +
            "AND s.lastUpdated >= :updateThreshold ORDER BY s.lastUpdated DESC")
    List<Song> findRecentSongs(@Param("stationName") String stationName,
                               @Param("updateThreshold")LocalDateTime updateThreshold);
}
