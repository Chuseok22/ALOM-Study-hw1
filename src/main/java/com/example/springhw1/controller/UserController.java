package com.example.springhw1.controller;

import com.example.springhw1.entity.User;
import com.example.springhw1.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users/join")
    private User findUserById(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("nickname") String nickname) {
        return userService.saveUser(username, password, nickname);
    }

    @GetMapping("/users/id/{id}")
    private User findUserById(@PathVariable("id") Long id) {
        return userService.findUserById(id);
    }

    @GetMapping("/users/username/{username}")
    private User findUserByUsername(@PathVariable("username") String username) {
        return userService.findUserByUsername(username);
    }


}
