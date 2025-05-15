package com.online.store.service;

import com.online.store.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User registerUser(User user);
    List<User> getAllUsers();
    User getUserById(int id);
    Optional<User> findByUserName(String username);
}
