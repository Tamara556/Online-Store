package com.online.store.security;

import com.online.store.entity.User;
import com.online.store.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> byEmail = userService.findByUserName(username);
        if (byEmail.isPresent()) {
            User user = byEmail.get();
            return new CurrentUser(user);
        }
        throw new UsernameNotFoundException("User not found");
    }
    }

