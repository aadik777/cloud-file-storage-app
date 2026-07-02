package com.cloudstorage.backend.service;

import com.cloudstorage.backend.dto.LoginRequest;
import com.cloudstorage.backend.dto.LoginResponse;
import com.cloudstorage.backend.dto.UserRequest;
import com.cloudstorage.backend.dto.UserResponse;
import com.cloudstorage.backend.entity.User;
import com.cloudstorage.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponse register(UserRequest request) {

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        User savedUser = userRepository.save(user);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getFullName(),
                savedUser.getEmail()
        );
    }

    public LoginResponse login(LoginRequest request) {

        Optional<User> user = userRepository.findByEmail(request.getEmail());

        if (user.isPresent()
                && user.get().getPassword().equals(request.getPassword())) {

            return new LoginResponse("Login Successful");
        }

        return new LoginResponse("Invalid Email or Password");
    }
}