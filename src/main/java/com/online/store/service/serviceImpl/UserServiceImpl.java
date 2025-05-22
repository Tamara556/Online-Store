package com.online.store.service.serviceImpl;

import com.online.store.dto.UpdateUserRequest;
import com.online.store.dto.UserDto;
import com.online.store.entity.Role;
import com.online.store.entity.User;
import com.online.store.mapper.UserMapper;
import com.online.store.repository.UserRepository;
import com.online.store.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void update(UpdateUserRequest request, User user) {
        if (request.getUsername() != null) {
            user.setUsername(request.getUsername());
        }
        if (request.getEmail() != null) {
            user.setEmail(request.getEmail());
        }
    }

    @Override
    public UserDto updateUser(int id, UpdateUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        update(request, user);

        User updatedUser = userRepository.save(user);

        return userMapper.toDto(updatedUser);
    }

    public User registerUser(User request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        // Encode password before saving
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole(Role.USER); // or whatever default role
        return userRepository.save(user);
    }


    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
