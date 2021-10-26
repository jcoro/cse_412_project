package com.example.cse_412_project.controllers;

import com.example.cse_412_project.entities.impl.AppUser;
import com.example.cse_412_project.repositories.UserRepository;

import java.security.Principal;
import java.util.*;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * UserController.java - DESCRIPTION
 * Author: John Coronite
 * Date: 8/29/21
 **/
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<AppUser> getUsers() {
        return (List<AppUser>) userRepository.findAll();
    }

    @PostMapping("/users")
    void addUser(@RequestBody AppUser user) {
        userRepository.save(user);
    }
}
