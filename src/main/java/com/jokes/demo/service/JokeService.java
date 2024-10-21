package com.jokes.demo.service;

import java.util.List;
import com.jokes.demo.entity.Joke;
import com.jokes.demo.repository.JokeRepository;
import org.springframework.stereotype.Service;

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
