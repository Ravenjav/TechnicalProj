package com.softarex.technical_proj.services;

import com.softarex.technical_proj.entities.User;
import com.softarex.technical_proj.exceptions.ServiceException;
import com.softarex.technical_proj.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public final class UserService{
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    User findUserById(String email){
        Optional<User> userDB = userRepository.findById(email);
        return userDB.orElse(null);
    }

    public void saveUser(User user) throws ServiceException {
        if (findUserById(user.getEmail()) != null){
            throw new ServiceException("User with this email is exist");
        }
        userRepository.save(user);
    }


}
