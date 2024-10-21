package com.jokes.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import com.jokes.demo.entity.Joke;
import com.jokes.demo.repository.JokeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    @Async
    public void fetchAndSaveJokesInBatch() {
        log.info("Starting to fetch jokes in batches...");

        List<Joke> jokesBatch = new ArrayList<>();

        // Fetch 10 jokes
        IntStream
                .range(0, 10).forEach(i -> {
            Optional<Joke> jokeOptional = fetchJokeFromAPI();

                    jokeOptional.ifPresentOrElse(joke -> {
                        jokesBatch.add(joke); // Collect jokes first, filter duplicates later
                        log.info("Joke added to the batch (for filtering): {}", joke.getSetup());
                    }, () -> log.error("Failed to fetch joke in batch: {}", i + 1));
        });



        // Collect joke IDs to filter out duplicates in bulk
        List<Long> jokeIds = jokesBatch.stream()
                .map(Joke::getId)
                .toList();

        // Fetch existing jokes by ID in bulk
        List<Joke> existingJokes = jokeRepository.findAllById(jokeIds);

        // Collect existing joke IDs
        List<Long> existingJokeIds = existingJokes.stream()
                .map(Joke::getId)
                .toList();

        // Filter out duplicates before saving
        List<Joke> uniqueJokesBatch = jokesBatch.stream()
                .filter(joke -> !existingJokeIds.contains(joke.getId()))
                .toList();

        if (!uniqueJokesBatch.isEmpty()) {
            jokeRepository.saveAll(uniqueJokesBatch);
            log.info("Saved {} unique jokes to the database.", uniqueJokesBatch.size());
        } else {
            log.warn("No unique jokes to save (all were duplicates).");
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
