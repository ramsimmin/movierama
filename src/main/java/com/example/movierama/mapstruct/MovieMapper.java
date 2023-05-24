package com.example.movierama.mapstruct;


import com.example.movierama.dto.MovieDto;
import com.example.movierama.entity.Movie;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = MovieVoteMapper.class)
public interface MovieMapper {

    @Mapping(source = "id", target = "id", qualifiedByName = "StringToUuid")
    Movie dtoToEntity(MovieDto movieDTO);

    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = "timeToStringDuration")
    @Mapping(source = "user.name", target = "creatorName")
    @Mapping(source = "id", target = "id", qualifiedByName = "UuidToString")
    MovieDto entityToDto(Movie movie);

    List<MovieDto> entitiesToDtos(List<Movie> movies);

    @Named("timeToStringDuration")
    default String timeToStringConverter(Instant createdAt) {
        Instant now = Instant.now();
        long years = ChronoUnit.YEARS.between(createdAt.atZone(ZoneId.systemDefault()), now.atZone(ZoneId.systemDefault()) );
        long months = ChronoUnit.MONTHS.between(createdAt.atZone(ZoneId.systemDefault()), now.atZone(ZoneId.systemDefault()));
        long days = ChronoUnit.DAYS.between(createdAt, now);
        long hours = ChronoUnit.HOURS.between(createdAt, now);
        long minutes = ChronoUnit.MINUTES.between(createdAt, now);
        long seconds = ChronoUnit.SECONDS.between(createdAt, now);

        if (years > 0L) {
            return years == 1 ? years + " year ago" : years + " years ago";
        }

        if (months > 0L) {
            return months == 1 ? months + " month ago" : months + " months ago";
        }

        if (days > 0L) {
            return days == 1 ? days + " day ago" : days + " days ago";
        }

        if (hours > 0L) {
            return hours == 1 ? hours + " hour ago" : hours + " hours ago";
        }

        if (minutes > 0L) {
            return minutes == 1 ? minutes + " minute ago" : minutes + " minutes ago";
        }

        return seconds + " seconds ago";
    }



}
