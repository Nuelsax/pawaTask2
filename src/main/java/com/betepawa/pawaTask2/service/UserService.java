package com.betepawa.pawaTask2.service;


import com.betepawa.pawaTask2.dto.LoginDto;
import com.betepawa.pawaTask2.models.User;

public interface UserService {
    User saveUser(User user);
    User loginUser(LoginDto loginDto);
}