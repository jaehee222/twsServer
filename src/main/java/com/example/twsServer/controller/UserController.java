package com.example.twsServer.controller;

import com.example.twsServer.dto.UserDto;
import com.example.twsServer.service.UserService;
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
    public boolean checkId(@RequestParam String UserId) {
        return userService.idDoubleCheck(UserId);
    }

    // 로그인 API
    @PostMapping("/login")
    public boolean login(@RequestBody UserDto userDto) {
        return userService.login(userDto.getUserId(), userDto.getPassword());
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

    @PostMapping("/logout")
    public void logout() {
        // 로그아웃 관련 로직 구현
    }
}