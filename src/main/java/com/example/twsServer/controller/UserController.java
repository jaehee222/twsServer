package com.example.twsServer.controller;

import com.example.twsServer.dto.UserDto;
import com.example.twsServer.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // ID 중복 체크 API
    @GetMapping("/checkId")
    public ResponseEntity<Object> checkId(@RequestParam String userId) {
        boolean isJoinedID = userService.idDoubleCheck(userId);

        if (isJoinedID){
            return ResponseEntity.badRequest().body("ID already registered");
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body("available ID");
        }
    }

    // 로그인 API
    @PostMapping("/login")
    public ResponseEntity<Object> login(HttpSession session, @RequestBody UserDto userDto) {

        if (userService.login(userDto.getUserId(), userDto.getPassword())) {
            // 로그인 성공되면 세션 저장
            session.setAttribute("userId", userDto.getUserId());
            return ResponseEntity.status(HttpStatus.CREATED).body("login Success");
        } else {
            return ResponseEntity.badRequest().body("login fail");
        }
    }

    // 비밀번호로 사용자 찾기 API
    @GetMapping("/findByPassword")
    public ResponseEntity<Object> findByPassword(@RequestParam String email) {

        boolean isEmailSend = userService.findByPassword(email);

        if (isEmailSend){
            return ResponseEntity.badRequest().body("Email send error");
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body("email send Success");
        }
    }

    // 사용자 회원가입 API
    @PostMapping("/join")
    public ResponseEntity<Object> join(@RequestBody UserDto userDto) {
        if (userDto.getUserId() == null || userDto.getUserId().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("userId is null");
        }
        if (userDto.getPassword() == null || userDto.getPassword().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("password is null");
        }
        if (userDto.getNickName() == null || userDto.getNickName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("nickName is null");
        }
        if (userDto.getEmail() == null || userDto.getEmail().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("email is null");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("join Success");
    }

    @GetMapping("/logout")
    public void logout(HttpSession session) {
        // 로그인 세션 끊기
        session.invalidate();
    }
}