package com.online.store.service;

import com.online.store.dto.UpdateUserRequest;
import com.online.store.dto.UserDto;
import com.online.store.entity.User;
import com.online.store.mapper.UserMapper;
import com.online.store.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;


    public void update(UpdateUserRequest request, User user) {
        if (request.getUsername() != null) {
            user.setUsername(request.getUsername());
        }
        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }
    }

    public UserDto updateUser(int id, UpdateUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        update(request, user);

        User updatedUser = userRepository.save(user);

        return userMapper.toDto(updatedUser);
    }
}
