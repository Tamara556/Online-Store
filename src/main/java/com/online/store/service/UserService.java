package com.online.store.service;

import com.online.store.entity.User;

import java.util.List;

public interface UserService {
    User registerUser(User user);
    List<User> getAllUsers();
    User getUserById(Long id);
}
