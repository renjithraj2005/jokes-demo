package com.jokes.demo.mapper;

import java.util.List;
import com.jokes.demo.dto.JokesDto;
import com.jokes.demo.entity.Joke;
import org.mapstruct.Mapper;

import static org.mapstruct.NullValueMappingStrategy.RETURN_NULL;
import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE, nullValueMappingStrategy = RETURN_NULL)
public abstract class JokesMapper {

    public abstract JokesDto toJokesDto(Joke joke);
}
