package com.jokes.demo;

import com.jokes.demo.service.JokeAPIService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

@Slf4j
public class ContextStartedListener implements ApplicationListener<ContextRefreshedEvent> {

    private final JokeAPIService jokeAPIService;

    public ContextStartedListener(final JokeAPIService jokeAPIService) {
        this.jokeAPIService = jokeAPIService;
    }

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        log.info("ContextStartedListener.onApplicationEvent");
        jokeAPIService.fetchAndSaveJokesInBatch();
    }

}
