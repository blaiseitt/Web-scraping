package pl.buarzej.model;

public class Song {

    private String title;
    private String author;
    private String playedTime;
    private String playedDate;
    private String stationName;

    public Song(String title, String author, String playedTime, String playedDate, String stationName) {
        this.title = title;
        this.author = author;
        this.playedTime = playedTime;
        this.playedDate = playedDate;
        this.stationName = stationName;
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

    public String getPlayedTime() {
        return playedTime;
    }

    public void setPlayedTime(String playedTime) {
        this.playedTime = playedTime;
    }

    public String getPlayedDate() {
        return playedDate;
    }

    public void setPlayedDate(String playedDate) {
        this.playedDate = playedDate;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
}
