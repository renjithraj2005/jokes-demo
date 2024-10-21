package com.jokes.demo.controller;

import java.util.Collections;
import java.util.List;
import com.jokes.demo.dto.JokesDto;
import com.jokes.demo.entity.Joke;
import com.jokes.demo.mapper.JokesMapper;
import com.jokes.demo.service.JokeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class JokeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JokeService jokeService;

    @MockBean
    private JokesMapper jokesMapper;

    @Test
    void testGetJokes() throws Exception {
        // Arrange
        List<Joke> jokesList = Collections.singletonList(new Joke(1L, "General", "Setup", "Punchline"));
        JokesDto jokesDto = new JokesDto("General", "Setup", "Punchline");

        when(jokeService.getJokes(1)).thenReturn(jokesList);
        when(jokesMapper.toJokesDto(any(Joke.class))).thenReturn(jokesDto);

        // Act & Assert
        mockMvc.perform(get("/api/v1/jokes?count=1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].type").value("General"))
                .andExpect(jsonPath("$[0].setup").value("Setup"))
                .andExpect(jsonPath("$[0].punchline").value("Punchline"));
    }

    @Test
    void testGetJokesWithInvalidCount() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/api/v1/jokes?count=0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
