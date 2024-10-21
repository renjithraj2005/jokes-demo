package com.jokes.demo.entity;

import com.jokes.demo.entity.core.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import static java.lang.String.format;

@Entity
@Data
public class Joke extends BaseEntity<Long> {
    @Id
    private Long id;
    private String type;
    private String setup;
    private String punchline;

    @Override
    public String friendlyName() {
        return format("Joke  (ID: %d)", this.getId());
    }

}
