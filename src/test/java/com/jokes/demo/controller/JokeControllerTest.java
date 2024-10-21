package com.jokes.demo.controller;

import java.util.Collections;
import java.util.List;
import com.jokes.demo.dto.JokesDto;
import com.jokes.demo.entity.Joke;
import com.jokes.demo.exception.InvalidCountException;
import com.jokes.demo.mapper.JokesMapper;
import com.jokes.demo.service.JokeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class JokeControllerTest {

    @InjectMocks
    private JokesController jokeController;

    @Mock
    private JokeService jokeService;

    @Mock
    private JokesMapper jokesMapper; // Mock the mapper

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetJokes_WhenValidCount_ShouldReturnJokes() {
        int count = 1;
        Joke joke = new Joke(1L, "General", "Setup", "Punchline"); // Adjust the constructor if needed
        List<Joke> jokesList = Collections.singletonList(joke);
        List<JokesDto> jokesDtoList = Collections.singletonList(new JokesDto("General", "Setup", "Punchline"));

        // Mock the service and mapper
        when(jokeService.getJokes(count)).thenReturn(jokesList);
        when(jokesMapper.toJokesDto(joke)).thenReturn(jokesDtoList.get(0));

        ResponseEntity<List<JokesDto>> response = jokeController.getJokes(count);
        // Assert
        verify(jokeService).getJokes(count);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size()); // Expecting one joke
    }

    @Test
    void testGetJokes_WhenCountIsZero_ShouldThrowException() {
        // Arrange
        int count = 0;

        // Act & Assert
        Exception exception = org.junit.jupiter.api.Assertions.assertThrows(
                InvalidCountException.class,
                () -> jokeController.getJokes(count)
                                                                           );

        System.out.println(exception.getMessage());
        assert exception.getMessage().contains("Count must be greater than 0");
    }
}
