package com.imdb.basic.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.imdb.basic.model.Actor;
import com.imdb.basic.model.Movie;
import com.imdb.basic.model.Producer;
import com.imdb.basic.repository.ActorRepository;
import com.imdb.basic.repository.MovieRepository;
import com.imdb.basic.repository.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MovieService {

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${amazonProperties.bucketName}")
    private String bucketName;


    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private ProducerRepository producerRepository;


    public void saveMovie(String movieName, String releaseDate, String plot, String actor,
                          String producer, MultipartFile poster) throws ParseException, IOException {

        Movie movie = new Movie();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(poster.getSize());
        amazonS3.putObject(bucketName, poster.getOriginalFilename(), poster.getInputStream(), metadata);
        String posterUrl = amazonS3.getUrl(bucketName, poster.getOriginalFilename()).toString();

        String[] actors = actor.split(",");

        Set<Actor> actorSet = new HashSet<>();

        for (int i = 0; i < actors.length; i++) {

            String actorName = actors[i];

            Actor dbActor = actorRepository.findByname(actorName);
            actorSet.add(dbActor);

        }

        Producer producerDb = producerRepository.findByname(producer);

        movie.setYearOfRelease(releaseDate);
        movie.setName(movieName);
        movie.setPlot(plot);
        movie.setActor(actorSet);
        movie.setProducer(producerDb);
        movie.setPosterUrl(posterUrl);

        movieRepository.save(movie);

    }

    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }
}
