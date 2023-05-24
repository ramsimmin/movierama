package com.example.movierama.repository;

import com.example.movierama.entity.MovieVote;
import com.example.movierama.entity.MovieVoteId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieVoteRepository extends JpaRepository<MovieVote, MovieVoteId> {

}