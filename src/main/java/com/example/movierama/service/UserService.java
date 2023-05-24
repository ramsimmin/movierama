package com.example.movierama.service;

import com.example.movierama.dto.UserDto;
import com.example.movierama.entity.User;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);
}
