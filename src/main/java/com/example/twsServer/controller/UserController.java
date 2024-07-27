package com.example.twsServer.controller;

import com.example.twsServer.dto.UserDto;
import com.example.twsServer.exception.ValidationException;
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

    @GetMapping("/test")
    public String test(){
        return "Connect Success!";
    }

    // ID 중복 체크 API
    @GetMapping("/checkId")
    public boolean checkId(@RequestParam String userId) {
        return userService.idDoubleCheck(userId);
    }

    // 로그인 API
    @PostMapping("/login")
    public boolean login(HttpSession session, @RequestBody UserDto userDto) {

        if (userService.login(userDto.getUserId(), userDto.getPassword())) {
            // 로그인 성공되면 세션 저장
            session.setAttribute("userId", userDto.getUserId());
            return true;
        } else {
            return false;
        }
    }

    // 비밀번호로 사용자 찾기 API
    @GetMapping("/findByPassword")
    public ResponseEntity<Object> findByPassword(@RequestParam String email) {
        
        if (email == null) {
            ResponseEntity.badRequest().body("email is null");
        }
        // 나중에 이메일 전송 로직 구현 필요
        return ResponseEntity.status(HttpStatus.CREATED).body("send Email!");
    }

    // 사용자 회원가입 API
    @PostMapping("/join")
    public ResponseEntity<Object> join(HttpSession session, @RequestBody UserDto userDto) {

        String userId = (String) session.getAttribute("userId");

        if (userId == null) {
            // 회원가입..
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
        }

        if (userService.join(userId, userDto) != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("join Success");
        } else {
            return ResponseEntity.badRequest().body("join fail!");
        }
    }

    @GetMapping("/logout")
    public void logout(HttpSession session) {
        // 로그인 세션 끊기
        session.invalidate();
    }
}