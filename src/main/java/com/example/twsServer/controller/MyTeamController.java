package com.example.twsServer.controller;

import com.example.twsServer.entity.MyTeamEntity;
import com.example.twsServer.entity.TeamEntity;
import com.example.twsServer.service.MyTeamService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/myTeam")
public class MyTeamController {

    private final MyTeamService myTeamService;

    @Autowired
    public MyTeamController(MyTeamService myTeamService) {
        this.myTeamService = myTeamService;
    }

    @GetMapping("/user")
    public List<MyTeamEntity> getMyTeam(HttpSession session) {
        if (session != null) {
            String userId = (String) session.getAttribute("userId");
            if (userId != null) {
                System.out.println("userId: " + userId);
                return myTeamService.findByUserId(userId);
            } else {
                System.out.println("userId가 존재하지 않습니다.");
                return null;
            }
        } else {
            System.out.println("세션이 존재하지 않습니다.");
            return null;
        }
    }

}
