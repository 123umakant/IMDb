package com.imdb.basic.dto;

import org.springframework.web.multipart.MultipartFile;

public class UpdateMovieDto {

    private String id;
    private String movie;
    private String releaseDate;
    private String actor;
    private String plot;
    private String producer;
    private MultipartFile poster;

    public UpdateMovieDto() {

    }

    public UpdateMovieDto(String id, String movie, String releaseDate, String actor, String plot, String producer, MultipartFile poster) {
        this.id = id;
        this.movie = movie;
        this.releaseDate = releaseDate;
        this.actor = actor;
        this.plot = plot;
        this.producer = producer;
        this.poster = poster;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public MultipartFile getPoster() {
        return poster;
    }

    public void setPoster(MultipartFile poster) {
        this.poster = poster;
    }
}
