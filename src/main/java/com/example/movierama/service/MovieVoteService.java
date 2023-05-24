package com.example.movierama.service;

public interface MovieVoteService {
    void saveMovieVote(String movieId, String email, Boolean vote);

    void deleteMovieVote(String movieId, String email);

}
