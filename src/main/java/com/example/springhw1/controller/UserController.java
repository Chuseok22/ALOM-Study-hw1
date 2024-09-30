package com.example.springhw1.controller;


import com.example.springhw1.entity.User;
import com.example.springhw1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService){this.userService = userService; }
    @GetMapping("/user/join")
    public User join(@RequestParam("username") String username,@RequestParam("password")String password,@RequestParam("nickname")String nickname){
        return userService.saveUser(username,password,nickname);
    }
    @GetMapping("user/endpoint/{username}")
    public User find(@PathVariable("username")String name){return userService.findUserByUsername(name);}




}
