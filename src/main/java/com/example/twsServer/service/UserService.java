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
    // 중복확인
    public boolean idDoubleCheck(String UserId){
        if (userRepository.existsByUserId(UserId)) {
            return true;
        }else{
            return false;
        }
    }
    public boolean NickNameDoubleCheck(String NickName){
        if (userRepository.existsByNickName(NickName)) {
            return true;
        }else{
            return false;
        }
    }
    public boolean emailDoubleCheck(String Email){
        if (userRepository.existsByEmail(Email)) {
            return true;
        }else{
            return false;
        }
    }

    // 내정보
    public UserDto myInfo(String UserId) {
        UserEntity myUser = userRepository.findByUserId(UserId);
        try {
            if (myUser != null) {
            return convertToDto(myUser);
            } else {
                throw new ValidationException("user 정보가 없습니다.");
            }
        } catch (ValidationException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }

    // 회원가입
    // 세션유무로 회원가입/회원정보수정으로 ..
    public UserDto join(String userId, UserDto userDto) {

        UserEntity user = null;
        try {

            if (userId == null) {
                // 회원가입
                user = new UserEntity();

                user.setUserId(userDto.getUserId());
                user.setPassword(userDto.getPassword());
                user.setNickName(userDto.getNickName());
                user.setEmail(userDto.getEmail());
                user.setRegDate(new Date());            // 가입일 자동셋팅

            } else {
                // 회원수정
                UserEntity existingUser = userRepository.findByUserId(userId);
                if (existingUser != null) {
                    user = existingUser;
                    if (userDto.getNickName() != null) {
                        user.setNickName(userDto.getNickName());
                    }
                    if (userDto.getEmail() != null) {
                        user.setEmail(userDto.getEmail());
                    }
                } else {
                    throw new ValidationException("사용자를 찾을 수 없습니다!");
                }
            }
            userRepository.save(user);
            return userDto;

        } catch (ValidationException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
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

    // 비밀번호 변경
    public boolean changePassword(String userId, UserDto userDto) {
        boolean isCorrect = login(userId, userDto.getPassword());
        if (isCorrect) {
            UserEntity user = userRepository.findByUserId(userId);
            user.setPassword(userDto.getChangePassword());
            userRepository.save(user);

            return true;
        }
        return false;
    }

    // 회원정보(닉네임) 변경
    public boolean changeInfo(String userId, UserDto userDto) {
        UserEntity user = userRepository.findByUserId(userId);
        user.setNickName(userDto.getNickName());
        userRepository.save(user);

        return false;
    }

    // 필요할때 사용 .. 아님 지우기
    private UserDto convertToDto(UserEntity userEntity) {
        UserDto userDto = new UserDto();

        userDto.setUserId(userEntity.getUserId());
        userDto.setNickName(userEntity.getNickName());
        // 비번은 안돼..
        // userDto.setPassword(userEntity.getPassword());
        userDto.setEmail(userEntity.getEmail());
        userDto.setRegDate(userEntity.getRegDate());

        return userDto;
    }
}
