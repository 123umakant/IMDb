package com.imdb.basic.controller;


import com.imdb.basic.repository.repositoryImp.MovieCacheImpl;
import com.imdb.basic.service.ProducerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProducerControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ProducerController producerController;

    @Mock
    private ProducerService producerService;
    @Mock
    private MovieCacheImpl movieCache;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(producerController).build();
    }

    @Test
    public void testGetAllProducer() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8080/api/v1/producer/producers")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testSaveProducer() throws Exception {

        String json = "{\n" +
                "  \"name\": \"Test\",\n" +
                "  \"sex\": \"male\",\n" +
                "  \"dob\": \"1980-01-11\",\n" +
                "  \"bio\": \"this is bio\"\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/v1/producer/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());
    }

}