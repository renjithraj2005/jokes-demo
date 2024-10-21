package com.jokes.demo.controller;

import java.util.List;
import com.jokes.demo.entity.Joke;
import com.jokes.demo.service.JokeService;
import com.jokes.demo.utils.MappingConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(MappingConstants.JOKE_API)
@Tag(name = "Building", description = "Endpoints for jokes")
public class JokesController {

    private final JokeService jokeService;

    public JokesController(final JokeService jokeService) {
        this.jokeService = jokeService;
    }

    @Operation(summary = "Get a list of jokes")
    @GetMapping("/jokes")
    public ResponseEntity<List<Joke>> getJokes(@RequestParam(defaultValue = "1") int count) {
        List<Joke> jokes = jokeService.getJokes(count);
        return ResponseEntity.ok(jokes);
    }
}
