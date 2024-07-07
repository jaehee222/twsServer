package com.example.twsServer.service;

import com.example.twsServer.dto.TeamDto;
import com.example.twsServer.entity.MyTeamEntity;
import com.example.twsServer.entity.TeamEntity;
import com.example.twsServer.repository.MyTeamRepository;
import com.example.twsServer.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyTeamService {
    private final MyTeamRepository myTeamRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public MyTeamService(MyTeamRepository myTeamRepository, TeamRepository teamRepository) {
        this.myTeamRepository = myTeamRepository;
        this.teamRepository = teamRepository;
    }
    // MyTeam 조회
    public List<TeamDto> findByUserId(String userId) {
        List<MyTeamEntity> myTeams = myTeamRepository.findByUserId(userId);
        List<TeamDto> teamDtoList = new ArrayList<>();
        for (MyTeamEntity myTeam : myTeams) {
            TeamEntity teamEntity = teamRepository.findByTeamNo(myTeam.getTeamNo());
            TeamDto teamDto = convertToDto(teamEntity);
            teamDtoList.add(teamDto);
        }
        return teamDtoList;
    }

    // TeamEntity를 TeamDto로 변환하는 메서드
    public TeamDto convertToDto(TeamEntity teamEntity) {
        TeamDto teamDto = new TeamDto();
        teamDto.setTeamNo(teamEntity.getTeamNo());
        teamDto.setTeamName(teamEntity.getTeamName());
        teamDto.setSportsKind(teamEntity.getSportsKind());
        teamDto.setPlace(teamEntity.getPlace());
        return teamDto;
    }
}
