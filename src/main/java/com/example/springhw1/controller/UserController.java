package com.example.springhw1.controller;


import com.example.springhw1.entity.User;
import com.example.springhw1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController //HTTP 요청, JSON 반환
@RequestMapping("/users")//이 클래스의 모든 메서드 /users 경로 사용
public class UserController {

    private final UserService userService;

    @Autowired//의존성 주입
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/join")//데이터 전송하거나 생성할 때
    public User 회원가입(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String nickname) {

        User savedUser = userService.saveUser(username, password, nickname);
        return savedUser;
    }

    @GetMapping("/id/{id}")//데이터 조회할 때
    public User getUserById(@PathVariable Long id){//변수 값 추출
        return userService.findUserById(id);
    }

    @GetMapping("/username/{username}")
    public User getUserByUsername(@PathVariable String username){
        return userService.findUserByUsername(username);
    }

}