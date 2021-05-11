package com.imdb.basic.repository;


import com.imdb.basic.model.Movie;

import java.util.Map;
import java.util.Set;

public interface MovieCacheRepo {

    Set getKey();

    void save(Movie movie);

    Map<String, Movie> findAll();

    void update(Movie movie);

    void delete(Integer id);

    Movie findById(Integer id);
}
