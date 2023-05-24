package com.example.movierama.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {

    private String id;

    @NotEmpty(message = "Title should not be empty")
    @Size(max = 255, message = "Max 255 chars accepted")
    private String title;

    @NotEmpty(message = "Description should not be empty")
    @Size(max = 1000, message = "Max 1000 chars accepted")
    private String description;

    private String email;

    private String creatorName;

    private List<MovieVoteDto> votes;

    private String createdAt;


}
