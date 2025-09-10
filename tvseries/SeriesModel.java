package com.mycompany.tvseries;

public class SeriesModel {
    private final String seriesId;             // unique identifier
    private String seriesName;           // title of the series
    private int seriesAge;               // age restriction (2..18)
    private int seriesNumberOfEpisodes;  // episode count

    public SeriesModel(String seriesId, String seriesName, int seriesAge, int seriesNumberOfEpisodes) {
        this.seriesId = seriesId;
        this.seriesName = seriesName;
        this.seriesAge = seriesAge;
        this.seriesNumberOfEpisodes = seriesNumberOfEpisodes;
    }

    public String getSeriesId() { return seriesId; }
    public String getSeriesName() { return seriesName; }
    public int getSeriesAge() { return seriesAge; }
    public int getSeriesNumberOfEpisodes() { return seriesNumberOfEpisodes; }

    public void setSeriesName(String seriesName) { this.seriesName = seriesName; }
    public void setSeriesAge(int seriesAge) { this.seriesAge = seriesAge; }
    public void setSeriesNumberOfEpisodes(int seriesNumberOfEpisodes) { this.seriesNumberOfEpisodes = seriesNumberOfEpisodes; }
}



