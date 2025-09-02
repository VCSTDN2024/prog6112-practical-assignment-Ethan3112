/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tvseriesapp;

/**
 *
 * @author lab_services_student
 */
public class SeriesModel {
    private String seriesId;
    private String seriesName;
    private int seriesAge;
    private int seriesNumberOfEpisodes;

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
    public void setSeriesNumberOfEpisodes(int seriesNumberOfEpisodes) {
        this.seriesNumberOfEpisodes = seriesNumberOfEpisodes;
    }
}
