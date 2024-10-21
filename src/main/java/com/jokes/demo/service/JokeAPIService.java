package com.jokes.demo.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import com.jokes.demo.entity.Joke;
import com.jokes.demo.repository.JokeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class JokeAPIService {

    private static final String JOKE_API_URL = "https://official-joke-api.appspot.com/random_joke";

    private final JokeRepository jokeRepository;
    private final RestTemplate restTemplate;

    public JokeAPIService(final JokeRepository jokeRepository) {
        this.jokeRepository = jokeRepository;
        this.restTemplate = new RestTemplate();
    }

    public void fetchAndSaveJokesInBatch() {
        log.info("Starting to fetch jokes in batches...");

        for (int i = 0; i < 10; i++) {
            Optional<Joke> jokeOptional = fetchJokeFromAPI();

            if (jokeOptional.isPresent()) {
                Joke joke = jokeOptional.get();

                // Save only if it's not a duplicate
                if (!jokeRepository.existsById(joke.getId())) {
                    jokeRepository.save(joke);
                    log.info("Saved joke: {}", joke.getSetup());
                } else {
                    log.info("Duplicate joke detected, skipping: {}", joke.getSetup());
                }
            } else {
                log.error("Failed to fetch joke in batch: {}", i + 1);
            }
        }

        log.info("Finished fetching and saving jokes.");
    }
    
    public Optional<Joke> fetchJokeFromAPI() {
        try {
            ResponseEntity<Joke> response = restTemplate.getForEntity(JOKE_API_URL, Joke.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                log.info("Successfully fetched joke: {}", response.getBody().getSetup());
                return Optional.of(response.getBody());
            } else {
                log.error("Error fetching joke: {}", response.getStatusCode());
            }
        } catch (Exception e) {
            log.error("Error occurred while calling the joke API: {}", e.getMessage());
        }

        return Optional.empty();
    }

}
