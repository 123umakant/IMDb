package com.imdb.basic.dto;

import com.imdb.basic.model.Actor;
import com.imdb.basic.model.Movie;
import com.imdb.basic.model.Producer;

import java.util.List;
import java.util.Optional;

public class MovieDto {

    private Optional<Movie> movie;
    private List<Producer> producerList;
    private List<Actor> actors;

    public Optional<Movie> getMovie() {
        return movie;
    }

    public void setMovie(Optional<Movie> movie) {
        this.movie = movie;
    }

    public List<Producer> getProducerList() {
        return producerList;
    }

    public void setProducerList(List<Producer> producerList) {
        this.producerList = producerList;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }
}
