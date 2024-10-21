package com.jokes.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JokesDto {
    private String type;
    private String setup;
    private String punchline;
}
