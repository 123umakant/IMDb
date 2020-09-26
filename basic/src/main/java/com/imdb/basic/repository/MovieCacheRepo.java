package com.imdb.basic.repository;


import com.imdb.basic.model.MovieCache;

import java.util.Map;
import java.util.Set;

public interface MovieCacheRepo {

    Set getKey();

    void save(MovieCache movie);

    Map<String, MovieCache> findAll();

    void update(MovieCache movie);

    void delete(Integer id);

    MovieCache findById(Integer id);
}
