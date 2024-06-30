package com.example.twsServer.controller;

import com.example.twsServer.entity.User;
import com.example.twsServer.DTO.UserDTO;
import com.example.twsServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    // 리엑트에서 spring 호출가능한지 테스트
    @GetMapping("/sample")
    public String getSampleMessage(){
        return "im spring";
    }

    @Autowired
    private UserService userService;

    @PostMapping ("/idDubleCheck")
    public boolean idDoubleCheck(@RequestParam String id){
        return userService.idDubleCheck(id);
    }

    @PostMapping("/login")
    public boolean login(@RequestParam String id, @RequestParam String password){
        return userService.login(id, password);
    }

    @PostMapping("/findByPassword")
    public User findByPassword(@RequestParam String Email){
        return userService.findByPassword(Email);
    }

    @PostMapping("/join")
    public User join(@RequestBody UserDTO userDTO){
        return userService.join(userDTO);
    }

    @GetMapping("/logout")
    public void logout(){}  //
}
