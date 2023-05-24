package com.example.movierama.service;

import com.example.movierama.dto.MovieDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface MovieService {
    void saveMovie(MovieDto movieDto);
    Page<MovieDto> findPaginated(PageRequest of);
    Page<MovieDto> findByEmailPaginated(String email, PageRequest of);
}
