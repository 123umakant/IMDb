package com.imdb.basic.model;

import java.io.Serializable;

public class MovieCache implements Serializable {

    private Integer id;
    private String name;
    private String yearOfRelease;
    private String plot;

    public MovieCache() {
    }

    public MovieCache(Integer id, String name, String yearOfRelease, String plot, String posterUrl) {
        this.id = id;
        this.name = name;
        this.yearOfRelease = yearOfRelease;
        this.plot = plot;
        this.posterUrl = posterUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(String yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    private String posterUrl;

    @Override
    public String toString() {
        return "MovieCache{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", yearOfRelease='" + yearOfRelease + '\'' +
                ", plot='" + plot + '\'' +
                ", posterUrl='" + posterUrl + '\'' +
                '}';
    }
}