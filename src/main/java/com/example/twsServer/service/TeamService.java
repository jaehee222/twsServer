package com.example.twsServer.service;

import com.example.twsServer.dto.TeamDto;
import com.example.twsServer.entity.TeamEntity;
import com.example.twsServer.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository){
        this.teamRepository = teamRepository;
    }

    public TeamDto findTeamInfo(int teamNo) {
        TeamEntity teamInfo = teamRepository.findByTeamNo(teamNo);

        return convertToDto(teamInfo);
    }

    private TeamDto convertToDto(TeamEntity teamEntity) {
        TeamDto teamDto = new TeamDto();

        teamDto.setTeamNo(teamEntity.getTeamNo());
        teamDto.setTeamName(teamEntity.getTeamName());
        teamDto.setSportsKind(teamEntity.getSportsKind());
        teamDto.setPlace(teamEntity.getPlace());

        return teamDto;
    }

    public List<TeamDto> getAllTeams() {
        List<TeamEntity> teams = teamRepository.findAll();
        return teams.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
