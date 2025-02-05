package pl.buarzej.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    //TODO List<author>
    private String author;
    private String playedHour;
    private String playedDate;

    //TODO lastUpdated should be equal for all elements that are updated in batch
    private LocalDateTime lastUpdated;

    @PrePersist
    @PreUpdate
    public void setTimestamp() {
        this.lastUpdated = LocalDateTime.now();
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "station_id")
    private StationDetails stationDetails;
    //TODO List of genres that song is
    //TODO Save scraped songs into some DB

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
}
