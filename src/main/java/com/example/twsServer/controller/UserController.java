package com.example.twsServer.controller;

import com.example.twsServer.dto.UserDto;
import com.example.twsServer.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Cipher;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

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

    @GetMapping("/myInfo")
    public ResponseEntity<Object> myInfo(HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.myInfo(userId));
    }

    @GetMapping("/checkNickname")
    public boolean checkNickname(@RequestParam String nickName) {
        return userService.NickNameDoubleCheck(nickName);
    }

    @GetMapping("/checkEmail")
    public boolean checkEmail(@RequestParam String email) {
        return userService.emailDoubleCheck(email);
    }

    @GetMapping("/public-key")
    public String getPublicKey() throws Exception {
        // 공개 키 파일에서 공개 키를 가져옴
        return new String(Files.readAllBytes(Paths.get("public.pem")));
    }

    // 로그인 API
    @PostMapping("/login")
    public boolean login(HttpSession session, @RequestBody UserDto userDto) {
        try {
            // 암호화된 비밀번호를 복호화
            String decryptedPassword = decryptPassword(userDto.getPassword());

            // 로그인 시 복호화된 비밀번호 사용
            if (userService.login(userDto.getUserId(), decryptedPassword)) {
                // 로그인 성공 시 세션에 userId 저장
                session.setAttribute("userId", userDto.getUserId());
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 비밀 키를 가져오는 메서드
    private PrivateKey getPrivateKey() throws Exception {
        // 비밀 키 파일 경로 (경로를 실제 비밀 키 파일의 위치로 수정)
        String privateKeyPEM = new String(Files.readAllBytes(Paths.get("private.pem")));

        // 불필요한 헤더 제거
        privateKeyPEM = privateKeyPEM.replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s+", "");

        // Base64 디코딩 후 비밀 키 추출
        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyPEM);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    // 암호화된 비밀번호를 복호화하는 메서드
    private String decryptPassword(String encryptedPassword) throws Exception {
        PrivateKey privateKey = getPrivateKey();
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));
        return new String(decryptedBytes);
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

    // 비밀번호 변경
    @PostMapping("/changePw")
    public ResponseEntity<Object> changePassword(HttpSession session, @RequestBody UserDto userDto) {
        String userId = (String) session.getAttribute("userId");

        if (userService.changePassword(userId, userDto)) {
            return ResponseEntity.ok(userDto);
        } else {
            return ResponseEntity.badRequest().body("change password fail!");
        }
    }

    // 회원정보 변경
    @PostMapping("/changeInfo")
    public ResponseEntity<Object> changeUserInfo(HttpSession session, @RequestBody UserDto userDto) {
        String userId = (String) session.getAttribute("userId");

        if (userService.changeInfo(userId, userDto)) {
            return ResponseEntity.ok(userDto);
        } else {
            return ResponseEntity.badRequest().body("change user info fail!");
        }
    }

    @GetMapping("/logout")
    public void logout(HttpSession session) {
        // 로그인 세션 끊기
        session.invalidate();
    }
}