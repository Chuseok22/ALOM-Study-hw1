package com.example.springhw1.service;

import com.example.springhw1.entity.User;
import com.example.springhw1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    //s

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 세 개의 String 파라미터를 받아 DB에 해당 유저의 데이터를 저장합니다.
     *
     * @param username 회원 아이디
     * @param password 회원 비밀번호
     * @param nickname 회원 닉네임
     * @return User 객체
     * 엔티티를 리턴하는 경우 스프링이 알아서 json형식으로 변환합니다.
     * 따라서 User 객체에서 데이터를 꺼낼 필요없이 Controller에서 return하게되면 json 형식으로 출력됩니다.
     */
    public User saveUser(String username, String password, String nickname) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setNickname(nickname);

        userRepository.save(user);
        return user;
    }

    /**
     * 유저 id를 파라미터로 받아 해당 id의 유저를 User객체 형식으로 반환합니다.
     * @param id 회원 id
     * @return User 객체
     * 엔티티를 리턴하는 경우 스프링이 알아서 json형식으로 변환합니다.
     * 따라서 User 객체에서 데이터를 꺼낼 필요없이 Controller에서 return하게되면 json 형식으로 출력됩니다.
     */
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    /**
     * 유저 아이디 username을 파라미터로 받아 해당 아이디의 유저를 User객체 형식으로 반환합니다.
     * @param username 회원 아이디
     * @return User 객체
     * 엔티티를 리턴하는 경우 스프링이 알아서 json형식으로 변환합니다.
     * 따라서 User 객체에서 데이터를 꺼낼 필요없이 Controller에서 return하게되면 json 형식으로 출력됩니다.
     */
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}
