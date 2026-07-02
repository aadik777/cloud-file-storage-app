package com.cloudstorage.backend.controller;

import com.cloudstorage.backend.dto.LoginRequest;
import com.cloudstorage.backend.dto.LoginResponse;
import com.cloudstorage.backend.dto.UserRequest;
import com.cloudstorage.backend.dto.UserResponse;
import com.cloudstorage.backend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserResponse register(@RequestBody UserRequest request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }
}