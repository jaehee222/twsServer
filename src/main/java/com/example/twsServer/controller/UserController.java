package com.example.twsServer.controller;

import com.example.twsServer.dto.UserDto;
import com.example.twsServer.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;

import javax.crypto.Cipher;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final JavaMailSender mailSender;

    @Autowired
    public UserController(UserService userService, JavaMailSender mailSender) {
        this.userService = userService;
        this.mailSender = mailSender;
    }

    @GetMapping("/test")
    public String test() {
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
            // 로그인 시 복호화된 비밀번호 사용
            if (userService.login(userDto.getUserId(), userDto.getPassword())) {
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

        privateKeyPEM = privateKeyPEM.replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s+", "");

        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyPEM);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    // 암호화된 비밀번호를 복호화하는 메서드
    private String decryptPassword(String encryptedPassword) throws Exception {
        try {
            PrivateKey privateKey = getPrivateKey();
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));
            return new String(decryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("RSA encryption failed", e);
        }
    }

    private String encryptPassword(String password) {
        try {
            PrivateKey publicKey = getPrivateKey();
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encryptedBytes = cipher.doFinal(password.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("RSA encryption failed", e);
        }
    }

    // 비밀번호로 사용자 찾기 API
    @GetMapping("/findByPassword")
    public ResponseEntity<Object> findByPassword(@RequestParam String email) {

        if (email == null || email.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Email is null or empty");
        }

        UserDto user = userService.findByPassword(email);
        if (user == null) {
            return ResponseEntity.badRequest().body("User not found with given email");
        }

        String tempPassword = generateTempPassword();
        // String encryptedTempPassword = encryptPassword(tempPassword);

        // 임시 비밀번호로 사용자 비밀번호 업데이트
        user.setChangePassword(tempPassword);

        boolean isChangePw = userService.changePassword(user.getUserId(), user, "findPw");

        if (isChangePw) {
            // 이메일 전송
            sendTemporaryPasswordEmail(email, tempPassword);

            return ResponseEntity.status(HttpStatus.CREATED).body("Success!");
        } else {
            return ResponseEntity.badRequest().body("error change Pw");
        }
    }

    private String generateTempPassword() {
        return UUID.randomUUID().toString().substring(0, 8); // 8자리 임시 비밀번호 생성
    }

    private void sendTemporaryPasswordEmail(String email, String tempPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your Temporary Password");
        message.setText("Your temporary password is: " + tempPassword + "\nPlease login and change your password.");
        mailSender.send(message);
    }

    // 사용자 회원가입 API
    @PostMapping("/join")
    public ResponseEntity<Object> join(HttpSession session, @RequestBody UserDto userDto) {

        String userId = (String) session.getAttribute("userId");

        // 회원가입인데 세션 있으면 안됨.. 강제 끊어주기
        if (userId != null) {
            session.invalidate();
        }

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

        if (userService.changePassword(userId, userDto, "changeInfo")) {
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