package com.example.twsServer.controller;

import com.example.twsServer.dto.MyTeamDto;
import com.example.twsServer.service.MyTeamService;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.net.HttpCookie;

@RestController
@RequestMapping("/MyTeam")
public class MyTeamController {

    private MyTeamService myTeamService;

    @GetMapping("/{userId}")
    public void getSampleMessage(HttpSession session, @PathVariable String userId) {
        if (session != null) {
//            String userId = (String) session.getAttribute("userId");
            if (userId != null) {
                System.out.println("userId: " + userId);
            } else {
                System.out.println("userId가 존재하지 않습니다.");
            }
        } else {
            System.out.println("세션이 존재하지 않습니다.");
        }
    }

    @GetMapping("{userId}/teams")
    public MyTeamDto getMyTeams(@PathVariable String userId) {
        return myTeamService.getMyTeam(userId);
    }

}
