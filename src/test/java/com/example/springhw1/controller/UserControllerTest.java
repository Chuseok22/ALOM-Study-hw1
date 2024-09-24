package com.example.springhw1.controller;

import com.example.springhw1.entity.User;
import com.example.springhw1.repository.UserRepository;
import com.example.springhw1.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.web.servlet.function.RequestPredicates.POST;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private User user;

    @BeforeEach
    void beforeEach() {
        user = User.builder()
                .id(1L)
                .username("testUsername")
                .password("testPW")
                .nickname("testNickname")
                .build();
    }

    @Test
    void 회원가입() throws Exception {

        // given
        Mockito.when((userService).saveUser(user.getUsername(), user.getPassword(), user.getNickname()))
                .thenReturn(user);

        // when
        mockMvc.perform(post("/users/join")
                        .param("username", user.getUsername())
                        .param("password", user.getPassword())
                        .param("nickname", user.getNickname())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())  // 상태 코드 200 확인
                .andExpect(jsonPath("$.id").value(user.getId()))  // 반환된 User의 ID 확인
                .andExpect(jsonPath("$.username").value(user.getUsername()))  // 반환된 User의 username 확인
                .andExpect(jsonPath("$.password").value(user.getPassword()))  // 반환된 User의 password 확인
                .andExpect(jsonPath("$.nickname").value(user.getNickname())); // 반환된 User의 nickname 확인
    }

    @Test
    void 회원_id_조회() throws Exception {
        // given
        Mockito.when(userService.findUserById(1L)).thenReturn(user);

        // when & then
        mockMvc.perform(get("/users/id/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user.getId()))  // 반환된 User의 ID 확인
                .andExpect(jsonPath("$.username").value(user.getUsername()))  // 반환된 User의 username 확인
                .andExpect(jsonPath("$.password").value(user.getPassword()))  // 반환된 User의 password 확인
                .andExpect(jsonPath("$.nickname").value(user.getNickname())); // 반환된 User의 nickname 확인
    }

    @Test
    void 회원_username_조회() throws Exception {
        // given
        Mockito.when(userService.findUserByUsername(user.getUsername())).thenReturn(user);

        // when & then
        mockMvc.perform(get("/users/username/{username}", user.getUsername())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user.getId()))  // 반환된 User의 ID 확인
                .andExpect(jsonPath("$.username").value(user.getUsername()))  // 반환된 User의 username 확인
                .andExpect(jsonPath("$.password").value(user.getPassword()))  // 반환된 User의 password 확인
                .andExpect(jsonPath("$.nickname").value(user.getNickname())); // 반환된 User의 nickname 확인
    }
}