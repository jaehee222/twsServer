package com.example.twsServer.service;

import com.example.twsServer.dto.MyTeamDto;
import com.example.twsServer.dto.TeamDto;
import com.example.twsServer.entity.MyTeamEntity;
import com.example.twsServer.entity.TeamEntity;
import com.example.twsServer.repository.MyTeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyTeamService {
    private MyTeamRepository myTeamRepository;

    // MyTeam 조회
    public MyTeamDto getMyTeam(String userId) {
//        List<TeamDto> teamDtoList = teams.stream()
//                .map(this::convertToTeamDto)
//                .collect(Collectors.toList());
        MyTeamEntity myTeamEntity = myTeamRepository.findByUserId(userId);

        return convertToDto(myTeamEntity);
    }

    private TeamDto convertToTeamDto(TeamEntity teamEntity) {
        TeamDto dto = new TeamDto();
        dto.setTeamNo(teamEntity.getTeamNo());
        dto.setTeamName(teamEntity.getTeamName());
        dto.setSportsKind(teamEntity.getSportsKind());
        dto.setPlace(teamEntity.getPlace());
        return dto;
    }

    private MyTeamDto convertToDto(MyTeamEntity myTeamEntity) {
        MyTeamDto myTeamDto = new MyTeamDto();
        myTeamDto.setUserId(myTeamEntity.getUserId());
        myTeamDto.setRegDate(myTeamEntity.getRegDate());
//        myTeamDto.setTeamDtoList(myTeamEntity.getMyTeamDtoList());

        return myTeamDto;
    }

}
