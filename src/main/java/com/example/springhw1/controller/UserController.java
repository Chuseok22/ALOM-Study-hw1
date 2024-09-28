package com.example.springhw1.controller;


import com.example.springhw1.entity.User;
import com.example.springhw1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService){this.userService =userService; }
    @GetMapping("/user/join")
    public void join(){
        userService.saveUser("이다예","1234","힘들다");
    }
    @GetMapping("user/endpoint/{username}")
    public User find(@PathVariable("username")String name){return userService.findUserByUsername(name);}




}
