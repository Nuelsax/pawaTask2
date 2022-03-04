package com.betepawa.pawaTask2.service;

import com.betepawa.pawaTask2.dto.LoginDto;
import com.betepawa.pawaTask2.models.User;
import com.betepawa.pawaTask2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User loginUser(LoginDto loginDto) {
        System.out.println("this is email" + loginDto.getEmail() + "password" + loginDto.getPassword() );
        return userRepository.getUserByEmailAndPassword(loginDto.getEmail(),loginDto.getPassword());

    }
}