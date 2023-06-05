package com.my.diplom.services;

import com.my.diplom.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public final class SecurityService implements UserDetailsService {
    private final UserService userService;

    public SecurityService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findUserById(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

}
