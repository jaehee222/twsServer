package com.example.twsServer.controller;

import com.example.twsServer.dto.TeamDto;
import com.example.twsServer.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/team")
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    // 팀정보 가져옴
    @PostMapping("/getInfo")
    public TeamDto findTeamInfo (@RequestBody TeamDto teamDto){
        int teamNo = teamDto.getTeamNo();
        return  teamService.findTeamInfo(teamNo);
    }
}
