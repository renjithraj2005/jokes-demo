package com.jokes.demo.repository;

import java.util.List;
import com.jokes.demo.entity.Joke;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JokeRepository extends JpaRepository<Joke, Long> {
    @Query(value = "SELECT * FROM jokes ORDER BY id ASC LIMIT ?1", nativeQuery = true)
    List<Joke> findFirstNByIdAsc(int count);
}
