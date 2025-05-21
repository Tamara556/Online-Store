package com.online.store.service;

import com.online.store.dto.UpdateUserRequest;
import com.online.store.dto.UserDto;
import com.online.store.entity.User;
import com.online.store.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void update(UpdateUserRequest request, User user) {
        if (request.getUsername() != null) {
            user.setUsername(request.getUsername());
        }
        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }
    }
    private UserDto mapToDTO(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        return dto;
    }

    public UserDto updateUser(int id, UpdateUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        update(request, user);

        User updatedUser = userRepository.save(user);

        return mapToDTO(updatedUser);
    }
}
