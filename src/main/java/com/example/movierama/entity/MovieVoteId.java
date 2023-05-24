package com.example.movierama.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class MovieVoteId implements Serializable {
    private UUID movieId;
    private String email;
}
