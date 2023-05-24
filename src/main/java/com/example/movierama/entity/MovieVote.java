package com.example.movierama.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "MOVIE_VOTES")
@IdClass(MovieVoteId.class)
public class MovieVote {
    @Id
    @Column(name = "movie_id", nullable = false)
    private UUID movieId;

    @Id
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "vote")
    private Boolean vote;

}
