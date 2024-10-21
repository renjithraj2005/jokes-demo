package com.jokes.demo.controller;

import java.util.List;
import com.jokes.demo.dto.JokesDto;
import com.jokes.demo.entity.Joke;
import com.jokes.demo.exception.InvalidCountException;
import com.jokes.demo.mapper.JokesMapper;
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
    private final JokesMapper jokesMapper;

    public JokesController(final JokeService jokeService, final JokesMapper jokesMapper) {
        this.jokeService = jokeService;
        this.jokesMapper = jokesMapper;
    }

    @Operation(summary = "Get a list of jokes")
    @GetMapping("/jokes")
    public ResponseEntity<List<JokesDto>> getJokes(@RequestParam(defaultValue = "1") int count) {
        if (count <= 0) {
            throw new InvalidCountException("Count must be greater than 0");
        }
        return ResponseEntity
                .ok()
                .body(jokeService
                        .getJokes(count)
                        .stream()
                        .map(jokesMapper::toJokesDto)
                        .toList());
    }
}
