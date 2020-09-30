package com.imdb.basic.controller;

import com.imdb.basic.dto.MovieDto;
import com.imdb.basic.dto.UpdateMovieDto;
import com.imdb.basic.model.Movie;
import com.imdb.basic.repository.repositoryImp.MovieCacheImpl;
import com.imdb.basic.service.ActorService;
import com.imdb.basic.service.MovieService;
import com.imdb.basic.service.ProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Autowired
    private ActorService actorService;

    @Autowired
    private ProducerService producerService;

    @Autowired
    private MovieCacheImpl movieCache;

    @GetMapping("get")
    public List<Movie> getMovie() {

        Logger logger = (Logger) LoggerFactory.getLogger(MovieController.class);
        logger.info(movieCache.findAll().toString());
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

    @GetMapping("fetch")
    public ResponseEntity<MovieDto> getMovieById(@RequestParam("id") Integer id) throws ParseException {

        MovieDto movieDto = new MovieDto();

        movieDto.setMovie(movieService.findById(id));
        movieDto.setActors(actorService.getActors());
        movieDto.setProducerList(producerService.getProducers());

        return new ResponseEntity(movieDto, HttpStatus.OK);
    }

    @PostMapping("update")
    public ResponseEntity update(@RequestParam("id") String id,
                                 @RequestParam("movie") String movie,
                                 @RequestParam("releaseDate") String releaseDate,
                                 @RequestParam("plot") String plot,
                                 @RequestParam("actor") String actor,
                                 @RequestParam("producer") String producer,
                                 @RequestParam(value = "image", required = false) MultipartFile poster)
            throws IOException, ParseException {

        UpdateMovieDto updateMovieDto = new UpdateMovieDto(id, movie, releaseDate, actor, plot, producer, poster);
        movieService.updateMovie(updateMovieDto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
