package com.example.movierama.service;

import com.example.movierama.entity.MovieVote;
import com.example.movierama.entity.MovieVoteId;
import com.example.movierama.repository.MovieVoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MovieVoteServiceImpl implements MovieVoteService {

    private final MovieVoteRepository movieVoteRepository;

    @Override
    public void saveMovieVote(String movieId, String email, Boolean vote) {
        MovieVote movieVote = MovieVote.builder().movieId(UUID.fromString(movieId)).email(email).vote(vote).build();
        movieVoteRepository.save(movieVote);
    }

    @Override
    public void deleteMovieVote(String movieId, String email) {
        MovieVoteId movieVoteId = MovieVoteId.builder().movieId(UUID.fromString(movieId)).email(email).build();
        movieVoteRepository.deleteById(movieVoteId);
    }
}
