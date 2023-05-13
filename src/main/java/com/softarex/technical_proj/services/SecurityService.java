package com.softarex.technical_proj.services;

import com.softarex.technical_proj.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("Start----------------------------------------------------------");
        User user = userService.findUserById(email);
        System.out.println(user.getAuthorityList().size());
        if (user == null){
            throw new UsernameNotFoundException("User not found");
        }
        System.out.println("---------------------------------------- ");
        return user;
    }

}
