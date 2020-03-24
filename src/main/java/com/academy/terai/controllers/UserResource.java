package com.academy.terai.controllers;

import com.academy.terai.model.User;
import com.academy.terai.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserResource {
    private UserRepository userRepository;

    public UserResource(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @GetMapping("/all")
    public List<User> getAll(){
        return userRepository.findAll();
    }
}
