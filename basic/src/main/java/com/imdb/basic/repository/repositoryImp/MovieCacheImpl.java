package com.imdb.basic.repository.repositoryImp;

import com.imdb.basic.model.Movie;
import com.imdb.basic.model.MovieCache;
import com.imdb.basic.repository.MovieCacheRepo;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Set;

@Repository
public class MovieCacheImpl implements MovieCacheRepo {

    private final String REDIS_CACHE_KEY = "MOVIE";

    private RedisTemplate<String, Movie> redisTemplate;

    private HashOperations hashOperations;

    public MovieCacheImpl(RedisTemplate<String, Movie> redisTemplate) {
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }


    @Override
    public Set getKey() {
       return hashOperations.keys(REDIS_CACHE_KEY);
    }

    @Override
    public void save(MovieCache movie) {

        hashOperations.put(REDIS_CACHE_KEY, movie.getId(), movie);
    }

    @Override
    public Map<String, MovieCache> findAll() {
        return hashOperations.entries(REDIS_CACHE_KEY);
    }

    @Override
    public void update(MovieCache movie) {
        save(movie);
    }

    @Override
    public void delete(Integer id) {
        hashOperations.delete(REDIS_CACHE_KEY, id);
    }

    @Override
    public MovieCache findById(Integer id) {
        return (MovieCache) hashOperations.get(REDIS_CACHE_KEY, id);
    }
}
