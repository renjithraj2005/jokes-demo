package com.jokes.demo.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import com.jokes.demo.entity.Joke;
import com.jokes.demo.repository.JokeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class JokesServiceTest {

    @Mock
    private JokeRepository jokeRepository;

    @InjectMocks
    private JokeService jokeService;

    private Joke joke1;
    private Joke joke2;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        joke1 = new Joke();
        joke1.setId(1L);
        joke1.setType("General");
        joke1.setSetup("Setup 1");
        joke1.setPunchline("Punchline 1");

        joke2 = new Joke();
        joke2.setId(2L);
        joke2.setType("Programming");
        joke2.setSetup("Setup 2");
        joke2.setPunchline("Punchline 2");
    }


    @Test
    void testGetJokes_WhenJokesFound() {
        // Arrange
        when(jokeRepository.findFirstNByIdAsc(2)).thenReturn(Arrays.asList(joke1, joke2));

        // Act
        List<Joke> jokes = jokeService.getJokes(2);

        // Assert
        assertEquals(2, jokes.size());
        assertEquals(joke1, jokes.get(0));
        assertEquals(joke2, jokes.get(1));
        verify(jokeRepository, times(1)).findFirstNByIdAsc(2);
    }

    @Test
    void testGetJokes_WhenNoJokesFound() {
        // Arrange
        when(jokeRepository.findFirstNByIdAsc(1)).thenReturn(Collections.emptyList());

        // Act
        List<Joke> jokes = jokeService.getJokes(1);

        // Assert
        assertTrue(jokes.isEmpty());
        verify(jokeRepository, times(1)).findFirstNByIdAsc(1);
    }

}

