package com.jokes.demo.service;

import com.jokes.demo.entity.Joke;
import com.jokes.demo.repository.JokeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class JokesServiceTest {

    @Autowired
    private JokeService jokeService;

    @Autowired
    private JokeRepository jokeRepository;

    @Test
    void testPreloadedData() {
        // Test that the preloaded jokes exist
        List<Joke> jokes = jokeService.getJokes(3);

        assertNotNull(jokes);
        assertEquals(3, jokes.size());
        assertEquals("Why did the chicken cross the road?", jokes.get(0).getSetup());
    }
}

