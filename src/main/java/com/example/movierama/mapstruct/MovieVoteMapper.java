package com.example.movierama.mapstruct;


import com.example.movierama.dto.MovieVoteDto;
import com.example.movierama.entity.MovieVote;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MovieVoteMapper {

    @Mapping(source= "movieId", target = "movieId", qualifiedByName = "UuidToString")
    MovieVoteDto entityToDto(MovieVote movieVote);

    @Mapping(source= "movieId", target = "movieId", qualifiedByName = "StringToUuid")
    MovieVote dtoToEntity(MovieVoteDto movieVoteDto);

    List<MovieVoteDto> entitiesToDtos(List<MovieVote> movies);

    @Named("UuidToString")
    default String uuidToString(UUID uuid) {
        return uuid.toString();
    }

    @Named("StringToUuid")
    default UUID stringToUuid(String string) {
        return UUID.fromString(string);
    }

}
