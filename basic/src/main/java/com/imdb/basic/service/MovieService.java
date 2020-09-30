package com.imdb.basic.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.imdb.basic.dto.UpdateMovieDto;
import com.imdb.basic.exception.ApiRequestException;
import com.imdb.basic.model.Actor;
import com.imdb.basic.model.Movie;
import com.imdb.basic.model.MovieCache;
import com.imdb.basic.model.Producer;
import com.imdb.basic.repository.ActorRepository;
import com.imdb.basic.repository.MovieRepository;
import com.imdb.basic.repository.ProducerRepository;
import com.imdb.basic.repository.repositoryImp.MovieCacheImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MovieService {

    private final int POSTER_NAME = 3;

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

    @Autowired
    private MovieCacheImpl movieCacheRepo;

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
            Optional<Actor> dbActor = actorRepository.findByname(actorName);
            actorSet.add(dbActor.get());
        }

       Optional<Producer> producerDb = producerRepository.findByname(producer);

        movie.setYearOfRelease(releaseDate);
        movie.setName(movieName);
        movie.setPlot(plot);
        movie.setActor(actorSet);
        movie.setProducer(producerDb.get());
        movie.setPosterUrl(posterUrl);

        MovieCache movieCache = new MovieCache();

        movieCache.setId(movieCacheRepo.getKey().size() + 1);
        movieCache.setName(movieName);
        movieCache.setPlot(plot);
        movieCache.setYearOfRelease(releaseDate);

        Optional<Movie> verifyMovie  = movieRepository.findByname(movieName);

        if (verifyMovie.get()!=null)
            throw new ApiRequestException("Movie is Already Present");

        movieCacheRepo.save(movieCache);
        movieRepository.save(movie);
    }

    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> findById(Integer id) {

        return movieRepository.findById(id);
    }

    public void updateMovie(UpdateMovieDto updateMovieDto) throws IOException {

        Integer movieId = Integer.parseInt(updateMovieDto.getId());

        Optional<Movie> movie = movieRepository.findById(movieId);

        movie.get().setName(updateMovieDto.getMovie());
        movie.get().setPlot(updateMovieDto.getPlot());
        movie.get().setYearOfRelease(updateMovieDto.getReleaseDate());

        String[] posterName = movie.get().getPosterUrl().split("/");

        amazonS3.deleteObject(bucketName, posterName[POSTER_NAME]);

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(updateMovieDto.getPoster().getSize());
        amazonS3.putObject(bucketName, updateMovieDto.getPoster().getOriginalFilename(), updateMovieDto.getPoster().getInputStream(), metadata);
        String posterUrl = amazonS3.getUrl(bucketName, updateMovieDto.getPoster().getOriginalFilename()).toString();

        movie.get().setPosterUrl(posterUrl);
        Optional<Producer> producerDb = producerRepository.findByname(updateMovieDto.getProducer());
        movie.get().setProducer(producerDb.get());

        String[] actors = updateMovieDto.getActor().split(",");

        Set<Actor> actorSet = new HashSet<>();

        for (int i = 0; i < actors.length; i++) {

            String actorName = actors[i];

            Optional<Actor> dbActor = actorRepository.findByname(actorName);

            actorSet.add(dbActor.get());

        }
        movie.get().setActor(actorSet);

        movieRepository.save(movie.get());

    }
}
