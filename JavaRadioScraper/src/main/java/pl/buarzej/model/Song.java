package pl.buarzej.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    //TODO List<author>

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String playedHour;

    @Column(nullable = false)
    private String playedDate;

    //TODO lastUpdated should be equal for all elements that are updated in batch
    private LocalDateTime lastUpdated;

    @PrePersist
    public void prePersist() {
        if (lastUpdated == null) {
            lastUpdated = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        }
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "station_id")
    private StationDetails stationDetails;
    //TODO List of genres that song is

    public Song() {}

    public Song(String title, String author, String playedHour, String playedDate, StationDetails stationDetails) {
        this.title = title;
        this.author = author;
        this.playedHour = playedHour;
        this.playedDate = playedDate;
        this.stationDetails = stationDetails;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPlayedHour() {
        return playedHour;
    }

    public void setPlayedHour(String playedHour) {
        this.playedHour = playedHour;
    }

    public String getPlayedDate() {
        return playedDate;
    }

    public void setPlayedDate(String playedDate) {
        this.playedDate = playedDate;
    }

    public StationDetails getStationDetails() {
        return stationDetails;
    }

    public void setStationDetails(StationDetails stationDetails) {
        this.stationDetails = stationDetails;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
