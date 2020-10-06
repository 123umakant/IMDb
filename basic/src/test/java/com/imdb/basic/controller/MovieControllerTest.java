package com.imdb.basic.controller;

import com.imdb.basic.dto.MovieDto;
import com.imdb.basic.repository.repositoryImp.MovieCacheImpl;
import com.imdb.basic.service.ActorService;
import com.imdb.basic.service.MovieService;
import com.imdb.basic.service.ProducerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class MovieControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private MovieController movieController;

    @Mock
    private MovieService movieService;

    @Mock
    private MovieDto movieDto;

    @Mock
    private ActorService actorService;

    @Mock
    private ProducerService producerService;

    @Mock
    private MovieCacheImpl movieCache;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
    }

    @Test
    public void testGetAllMovies() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8080/api/v1/movie/movies")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testSaveMovie() throws Exception {


        MockMultipartFile firstFile = new MockMultipartFile("image", "filename.txt",
                "multipart/form-data", "some xml".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("http://localhost:8080/api/v1/movie/save")
                .file(firstFile)
                .param("movie", "Test")
                .param("plot", "test")
                .param("actor", "test")
                .param("producer", "test")
                .param("releaseDate", "1990-01-01"))
                .andExpect(status().is(201));
    }

    @Test
    public void testGetMovieById() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8080/api/v1/movie/fetch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("id", "1")
        )
                .andExpect(status().isOk());
    }


    @Test
    public void testUpdateMovie() throws Exception {


        MockMultipartFile firstFile = new MockMultipartFile("image", "filename.txt",
                "multipart/form-data", "some xml".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("http://localhost:8080/api/v1/movie/update")
                .file(firstFile)
                .param("id", "1")
                .param("movie", "Test")
                .param("plot", "test")
                .param("actor", "test")
                .param("producer", "test")
                .param("releaseDate", "1990-01-01"))
                .andExpect(status().is(200));
    }
}
