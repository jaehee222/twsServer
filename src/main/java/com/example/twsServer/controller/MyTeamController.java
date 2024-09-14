package com.example.twsServer.controller;

import com.example.twsServer.dto.MyTeamDto;
import com.example.twsServer.dto.TeamDto;
import com.example.twsServer.dto.TicketDto;
import com.example.twsServer.entity.MyTeamEntity;
import com.example.twsServer.service.MyTeamService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/list")
    public List<TeamDto> getMyTeam(HttpSession session) {
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

    @PostMapping("/newMyTeam")
    public ResponseEntity<Object> addMyTeam(HttpSession session, @RequestBody MyTeamDto myTeamDto) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.badRequest().body("userId is null");
        }

        try {
            Integer teamNo = myTeamDto.getTeamNo();
            myTeamService.createMyTeam(userId, teamNo);
            return ResponseEntity.status(HttpStatus.CREATED).body(String.format("Create myTeam success(teamNo: %d)", teamNo));
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Create MyTeam failed" + e.getMessage());
        }
    }

    @GetMapping("/deleteMyTeam/{teamNo}")
    public ResponseEntity<Object> deleteMyTeam(HttpSession session, @PathVariable(value = "teamNo") Integer teamNo) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.badRequest().body("userId is null");
        }

        boolean isDelMyTeam = myTeamService.deleteMyTeam(userId, teamNo);
        if (isDelMyTeam) {
            return ResponseEntity.status(HttpStatus.CREATED).body(String.format("Delete myTeam success(teamNo: %d)", teamNo));
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(String.format("Delete myTeam failed(teamNo: %d)", teamNo));
        }
    }

    @PostMapping("/rate")
    public ResponseEntity<Object> getMyTeamRate(HttpSession session, @RequestBody MyTeamDto myTeamDto) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return null;
        }

        try {
            Integer teamNo = myTeamDto.getTeamNo();
            TeamDto teamDto = myTeamService.getTeamRate(userId, teamNo);
            return ResponseEntity.ok(teamDto);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("get MyTeam rate failed: " + e.getMessage());
        }
    }
}
