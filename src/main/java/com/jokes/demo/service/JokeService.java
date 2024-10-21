package com.jokes.demo.service;

import com.jokes.demo.entity.Joke;
import com.jokes.demo.repository.JokeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JokeService {

    private  final JokeRepository jokeRepository;

    public JokeService(final JokeRepository jokeRepository) {
        this.jokeRepository = jokeRepository;
    }

    public List<Joke> getJokes(int count) {
        // Fetch all jokes and limit the count
        return jokeRepository.findFirstNByIdAsc(count);
    }
}
