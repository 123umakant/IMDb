package com.imdb.basic.controller;

import com.imdb.basic.dto.ActorDto;
import com.imdb.basic.service.ActorService;
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
public class ActorControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ActorController actorController;

    @Mock
    private ActorService actorService;


    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(actorController).build();
    }

    @Test
    public void testGetAllActors() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8080/api/v1/actor/actors")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testSaveActor() throws Exception {

        String json = "{\n" +
                "  \"name\": \"Test\",\n" +
                "  \"sex\": \"male\",\n" +
                "  \"dob\": \"1980-01-11\"\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/v1/actor/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());
    }
}