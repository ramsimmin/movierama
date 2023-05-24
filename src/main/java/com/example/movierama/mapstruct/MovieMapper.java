package com.example.movierama.mapstruct;



import com.example.movierama.dto.MovieDto;
import com.example.movierama.entity.Movie;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = MovieVoteMapper.class)
public interface MovieMapper {

    @Mapping(source= "id", target = "id", qualifiedByName = "StringToUuid1")
    Movie dtoToEntity(MovieDto movieDTO);

    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "timeToStringDuration")
    @Mapping(source= "user.name", target = "creatorName")
    @Mapping(source= "id", target = "id", qualifiedByName = "UuidToString1")
    MovieDto entityToDto(Movie movie);

    List<MovieDto> entitiesToDtos(List<Movie> movies);

    @Named("timeToStringDuration")
    default String timeToStringConverter(Instant createdAt) {
        Duration res = Duration.between(createdAt, Instant.now());
        if (res.toDaysPart() == 0L) {
            return "today";
        }
        return res.toDaysPart() + " days ago";
    }

    @Named("UuidToString1") //TODO
    default String uuidToString(UUID uuid) {
        return uuid.toString();
    }

    @Named("StringToUuid1") //TODO
    default UUID stringToUuid(String string) {
        return UUID.fromString(string);
    }

}
