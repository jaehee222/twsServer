package com.example.twsServer.service;

import com.example.twsServer.dto.UserDto;
import com.example.twsServer.entity.UserEntity;
import com.example.twsServer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    // 아이디 중복확인
    public boolean idDoubleCheck(String UserId){
        if (userRepository.existsByUserId(UserId)) {
            return true;
        }else{
            return false;
        }
    }

    // 회원가입
    public UserDto join(UserDto userDto){

        UserEntity user = new UserEntity();
        user.setUserId(userDto.getUserId());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        userRepository.save(user);

        return userDto;
    }

    // 로그인
    public boolean login(String UserId, String password){
        UserEntity user = userRepository.findByUserId(UserId);
        // 맞는지 확인..
//        if (user == null || !passwordEncoder.matches(password, user.getPassWord())) {
//            return false;
//        }

        return true;
    }

    // 비밀번호 찾기
    public UserDto findByPassword(String Email) {
        UserEntity user = userRepository.findByEmail(Email);
        if (user == null) {
            throw new RuntimeException("Invalid email");
        }

        return convertToDto(user);
    }

    private UserDto convertToDto(UserEntity userEntity) {
        UserDto userDto = new UserDto();
        userDto.setUserId(userEntity.getUserId());
        userDto.setPassword(userEntity.getPassword());
        userDto.setEmail(userEntity.getEmail());
        userDto.setRegDate(userEntity.getRegDate());
        return userDto;
    }

    // 로그아웃..
    public void logout(UserDto userDto){
    }
}
