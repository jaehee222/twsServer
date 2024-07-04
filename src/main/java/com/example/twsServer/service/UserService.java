package com.example.twsServer.service;

import com.example.twsServer.dto.UserDto;
import com.example.twsServer.entity.UserEntity;
import com.example.twsServer.exception.ValidationException;
import com.example.twsServer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
        user.setNickName(userDto.getNickName());
        user.setEmail(userDto.getEmail());
        user.setRegDate(new Date());            // 가입일 자동셋팅

        userRepository.save(user);

        return userDto;
    }

    // 로그인
    public boolean login(String userId, String password){
        // 비밀번호 암호화로직은 추후 구현예정..
        UserEntity user = userRepository.findByUserId(userId);
        if (user == null) {
            throw new ValidationException("Invalid userId");
        }
        if (user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    // 비밀번호 찾기
    public boolean findByPassword(String Email) {
        UserEntity user = userRepository.findByEmail(Email);

        if (user == null){
            return false;
        }else{
            // 이메일 전송 로직은 추후 개발 .. 일단 email 매칭만 해둠
            return true;
        }
    }

    // 필요할때 사용 .. 아님 지우기
    private UserDto convertToDto(UserEntity userEntity) {
        UserDto userDto = new UserDto();

        userDto.setUserId(userEntity.getUserId());
        userDto.setPassword(userEntity.getPassword());
        userDto.setEmail(userEntity.getEmail());
        userDto.setRegDate(userEntity.getRegDate());

        return userDto;
    }
}
