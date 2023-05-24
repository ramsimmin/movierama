package com.example.movierama.repository;

import com.example.movierama.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface MovieRepository extends PagingAndSortingRepository<Movie, UUID> {

    void save(Movie movie);

    @Query(value = """
        select * from (
            select movies.*, likes.likes_count as likes, hates.hates_count as hates
            from movies
            
            left join
            (select count(*) as likes_count, movie_id from movie_votes
            where vote=TRUE  group by movie_id, vote) likes
            on movies.id = likes.movie_id
            
            left join
            (select count(*) as hates_count, movie_id from movie_votes
            where vote=FALSE group by movie_id, vote) hates
            on movies.id = hates.movie_id
        )
        """,
            countQuery = "SELECT count(*) FROM MOVIES",
            nativeQuery = true)
    Page<Movie> findAll(Pageable pageable);


    @Query(value = """
        select * from (
            select movies.*, likes.likes_count as likes, hates.hates_count as hates
            from movies
            
            left join
            (select count(*) as likes_count, movie_id from movie_votes
            where vote=TRUE  group by movie_id, vote) likes
            on movies.id = likes.movie_id
            
            left join
            (select count(*) as hates_count, movie_id from movie_votes
            where vote=FALSE group by movie_id, vote) hates
            on movies.id = hates.movie_id
            where email = :email
        )
        
        """,
            countQuery = "SELECT count(*) FROM MOVIES where email = :email",
            nativeQuery = true)
    Page<Movie> findAllByEmail(String email, Pageable pageable);

    Movie findById(UUID id);


}