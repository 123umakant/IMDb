package com.imdb.basic.controller;

import com.imdb.basic.model.Movie;
import com.imdb.basic.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;


@RestController
@RequestMapping("/api/v1/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;


    @GetMapping("get")
    public List<Movie> getMovie() {
      // List<Movie> movies = movieService.getMovies();

        return movieService.getMovies();
    }

    @PostMapping("/add")
    public ResponseEntity createMovie(@RequestParam("movie") String movie,
                                      @RequestParam("releaseDate") String releaseDate,
                                      @RequestParam("plot") String plot,
                                      @RequestParam("actor") String actor,
                                      @RequestParam("producer") String producer,
                                      @RequestParam(value = "image", required = false) MultipartFile poster)
            throws IOException, ParseException {


       movieService.saveMovie(movie, releaseDate, plot, actor, producer, poster);

        return new ResponseEntity(HttpStatus.CREATED);
    }
}
