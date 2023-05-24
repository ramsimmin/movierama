package com.example.movierama.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "USERS")
public class User {

    @Id
    private String email;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private String password;

    @Column(updatable = false)
    @CreationTimestamp
    private Instant createdAt;

}
