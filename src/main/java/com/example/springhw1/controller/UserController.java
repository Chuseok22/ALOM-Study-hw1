package com.example.springhw1.controller;

import com.example.springhw1.entity.User;
import com.example.springhw1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users/join")
    public User signIn(@RequestParam("username") String username, @RequestParam("password") String password,  @RequestParam("nickname") String nickname) {
        return userService.saveUser(username, password, nickname);
    }

    @GetMapping("/users/id/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        return userService.findUserById(id);
    }

    @GetMapping("/users/username/{username}")
    public User getUserByUsername(@PathVariable("username") String username) {
        return userService.findUserByUsername(username);
    }
}
