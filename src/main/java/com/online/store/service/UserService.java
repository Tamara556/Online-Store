package com.online.store.service;

import com.online.store.dto.UpdateUserRequest;
import com.online.store.dto.UserDto;
import com.online.store.entity.User;

import java.util.Optional;

public interface UserService {


    public void update(UpdateUserRequest request, User user);

    public UserDto updateUser(int id, UpdateUserRequest request);

    public User registerUser(User user);

    User save(User user);

    Optional<User> findByEmail(String email);
}
