package com.example.twsServer.service;

import com.example.twsServer.entity.User;
import com.example.twsServer.DTO.UserDTO;
import com.example.twsServer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    // 아이디 중복확인
    public boolean idDubleCheck(String userId){
        if (userRepository.existsByUserId(userId)) {
            return true;
        }else{
            return false;
        }
    }

    // 회원가입
    public User join(UserDTO userDTO){

        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setPassWord(passwordEncoder.encode(userDTO.getPassWord()));
        user.setEmail(userDTO.getEmail());

        return userRepository.save(user);
    }

    // 로그인
    public boolean login(String userId, String password){
        User user = userRepository.findByUserId(userId);
        // 맞는지 확인..
        if (user == null || !passwordEncoder.matches(password, user.getPassWord())) {
            return false;
        }

        return true;
    }

    // 비밀번호 찾기
    public User findByPassword(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("Invalid email");
        }
        return user;
    }

    // 로그아웃..
    public void logout(User user){
    }

}

