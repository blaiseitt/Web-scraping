package pl.buarzej.model;

public class Song {

    private String title;
    //TODO List<author>
    private String author;
    private String playedHour;
    private String playedDate;
    private StationDetails stationDetails;
    //TODO List of genres that song is
    //TODO Save scraped songs into some DB

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
