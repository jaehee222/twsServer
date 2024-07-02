package com.example.twsServer.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.HttpCookie;

@RestController
@RequestMapping("/MyTeam")
public class MyTeamController {
    @GetMapping("/test")
    public void getSampleMessage(HttpSession session) { // 세션값 잘 가지고 오는지 test..
        if (session != null) {
            String userId = (String) session.getAttribute("userId");
            if (userId != null) {
                System.out.println("userId: " + userId);
            } else {
                System.out.println("userId X");
            }
        } else {
            System.out.println("세션이 존재하지 않습니다.");
        }
    }
}
