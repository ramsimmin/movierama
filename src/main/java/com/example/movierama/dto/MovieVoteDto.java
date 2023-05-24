package com.example.movierama.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieVoteDto {
    private String movieId;
    private String email;
    private Boolean vote;
}
