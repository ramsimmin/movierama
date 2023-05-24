package com.example.movierama.service;

import com.example.movierama.dto.MovieDto;
import com.example.movierama.entity.Movie;
import com.example.movierama.mapstruct.MovieMapper;
import com.example.movierama.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    @Override
    public void saveMovie(MovieDto movieDto) {
        if (movieDto.getId() == null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Movie movie = Movie.builder().id(UUID.randomUUID()).title(movieDto.getTitle()).description(movieDto.getDescription()).email(auth.getName()).build();
            movieRepository.save(movie);
        }
    }

    @Override
    public Page<MovieDto> findPaginated(PageRequest of) {
        Page<Movie> moviePage = movieRepository.findAll(of);
        Page<MovieDto> movieDtoPage = moviePage.map(movie -> movieMapper.entityToDto(movie));
        return movieDtoPage;
    }

    @Override
    public Page<MovieDto> findByEmailPaginated(String email, PageRequest of) {
        Page<Movie> moviePage = movieRepository.findAllByEmail(email, of);
        Page<MovieDto> movieDtoPage = moviePage.map(movie -> movieMapper.entityToDto(movie));
        return movieDtoPage;
    }


}
