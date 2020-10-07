package com.imdb.basic.controller;

import com.imdb.basic.dto.MovieDto;
import com.imdb.basic.dto.MovieRequestDto;
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

    @GetMapping("/movies")
    public List<Movie> getMovie() {

        Logger logger = (Logger) LoggerFactory.getLogger(MovieController.class);
        logger.info(movieCache.findAll().toString());

        return movieService.getMovies();
    }


    @RequestMapping(path = "/save", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public ResponseEntity createMovie(@ModelAttribute MovieRequestDto movieRequestDto)
            throws IOException, ParseException {

        movieService.saveMovie(movieRequestDto);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(path = "/fetch/{id}", method = RequestMethod.GET)
    public ResponseEntity<MovieDto> getMovieById(@PathVariable final String id) throws ParseException {

        MovieDto movieDto = new MovieDto();

        movieDto.setMovie(movieService.findById(Integer.parseInt(id)));
        movieDto.setActors(actorService.getActors());
        movieDto.setProducerList(producerService.getProducers());

        return new ResponseEntity(movieDto, HttpStatus.OK);
    }

    @RequestMapping(path = "/update", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public ResponseEntity update(@ModelAttribute MovieRequestDto movieRequestDto)
            throws IOException, ParseException {

        movieService.updateMovie(movieRequestDto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
