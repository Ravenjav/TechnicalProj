package com.my.diplom.services;

import com.my.diplom.entities.Authority;
import com.my.diplom.entities.User;
import com.my.diplom.entities.UserInfo;
import com.my.diplom.exceptions.ServiceException;
import com.my.diplom.repository.UserInfoRepository;
import com.my.diplom.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserService{
    private final UserRepository userRepository;

    private final ItemService itemService;

    private final UserInfoRepository userInfoRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, ItemService itemService, UserInfoRepository userInfoRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.itemService = itemService;
        this.userInfoRepository = userInfoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findUserById(String email){
        Optional<User> userDB = userRepository.findById(email);
        return userDB.orElse(null);
    }

    public void saveNewUser(User user) throws ServiceException {
        if (findUserById(user.getEmail()) != null){
            throw new ServiceException("User with this email is exist");
        }
        user.setEnabled(true);
        List<Authority> authorityList = new ArrayList<>();
        Authority authority = new Authority();
        authority.setAuthority("ROLE_USER");
        authorityList.add(authority);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthorityList(authorityList);
        user.getUserInfo().setUser(user);
        System.out.println(user.getPassword());
        System.out.println("-----------------");
        userRepository.save(user);
    }
    @Transactional
    public User setNewUserInfo(User user, UserInfo info) throws ServiceException {
        if (user.getUserInfo().equals(info))
            throw  new ServiceException("you haven't changed anything");
        info.setId(user.getUserInfo().getId());
        info.setUser(user);
        user.setUserInfo(info);
        userInfoRepository.save(info);
        return user;
    }

    @Transactional
    public void deleteUser(User user){
        userRepository.delete(user);
    }

}
