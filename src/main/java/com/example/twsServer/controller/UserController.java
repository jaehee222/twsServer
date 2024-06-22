package com.example.twsServer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/twsServer")
public class UserController {

    // 리엑트에서 spring 호출가능한지 테스트
    @GetMapping("/sample")
    public String getSampleMessage(){
        return "im spring";
    }
}
