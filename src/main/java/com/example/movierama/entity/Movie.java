package com.example.movierama.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "MOVIES")
public class Movie {
    @Id
    private UUID id;

    @Column(nullable=false)
    private String title;

    @Column(nullable=false)
    private String description;

    @Column(name = "email", nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "email", referencedColumnName = "email", insertable = false, updatable = false)
    private User user;

    @OneToMany
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private List<MovieVote> votes;

    @Column(updatable = false)
    @CreationTimestamp
    private Instant createdAt;



}
