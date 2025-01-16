package com.example.Cinema.service;

import com.example.Cinema.dto.UserDto;
import com.example.Cinema.model.RegistrationRequest;
import com.example.Cinema.model.User;

import java.util.List;

public interface UserService {

    boolean checkEmail(String email);

    UserDto registerUser(RegistrationRequest registrationRequest);

    UserDto getLoginUser();

    UserDto getUserById(Integer id);

    List<UserDto> getAllUsers();

    UserDto createUser(User user);

    UserDto updateUser(User user);

    void deleteUser(User user);

    public void deleteUserByUsername(String usernames);

    public UserDto findUserByUsername(String username);
    public User findUserEntityByUsername(String username);
}
