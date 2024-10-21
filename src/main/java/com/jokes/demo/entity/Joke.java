package com.jokes.demo.entity;

import com.jokes.demo.entity.core.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static java.lang.String.format;

@Entity
@Data
@Table(name = "jokes")
@AllArgsConstructor
@NoArgsConstructor
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
