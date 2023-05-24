package com.example.movierama.util;

import com.example.movierama.dto.MovieVoteDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TextUtils {

    public Boolean isVotedByUser(List<MovieVoteDto> votes, String email) {
        Optional<MovieVoteDto> votedByUser = votes.stream().filter(movieVoteDto -> movieVoteDto.getEmail().equalsIgnoreCase(email)).findFirst();

        if (votedByUser.isPresent()) {
            return votedByUser.get().getVote();
        }
        return null;
    }

}
