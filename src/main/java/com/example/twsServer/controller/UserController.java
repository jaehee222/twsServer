package com.example.twsServer.controller;

import com.example.twsServer.dto.UserDto;
import com.example.twsServer.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 단순한 테스트용 API 예제
    @GetMapping("/test")
    public String getSampleMessage() {
        return "Hi, I'm Spring!";
    }

    // ID 중복 체크 API
    @GetMapping("/checkId")
    public boolean checkId(@RequestParam String userId) {
        return userService.idDoubleCheck(userId);
    }

    // 로그인 API
    @PostMapping("/login")
    public boolean login(HttpSession session, @RequestBody UserDto userDto) {

        if  (userService.login(userDto.getUserId(), userDto.getPassword())){
            // 로그인 성공되면 세션 저장
            session.setAttribute("userId", userDto.getUserId());

            return true;

        }else{
            return false;
        }
    }

    // 비밀번호로 사용자 찾기 API
    @GetMapping("/findByPassword")
    public UserDto findByPassword(@RequestParam String email) {
        return userService.findByPassword(email);
    }

    // 사용자 회원가입 API
    @PostMapping("/join")
    public UserDto join(@RequestBody UserDto userDto) {
        return userService.join(userDto);
    }

    @GetMapping("/logout")
    public void logout(HttpSession session) {
        // 로그인 세션 끊기
        session.invalidate();
    }
}