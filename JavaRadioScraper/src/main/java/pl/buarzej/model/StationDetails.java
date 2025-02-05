package pl.buarzej.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class StationDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;
    private String name;
    private String displayName;

    @OneToMany(mappedBy = "stationDetails", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Song> songs;

    public StationDetails() {}

    public StationDetails(String name, String url, String displayName) {
        this.url = url;
        this.displayName = displayName;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public String getDisplayName() {
        return displayName;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
