package com.example.twsServer.service;

import com.example.twsServer.dto.MyTeamDto;
import com.example.twsServer.dto.TeamDto;
import com.example.twsServer.entity.MyTeamEntity;
import com.example.twsServer.entity.MyTeamId;
import com.example.twsServer.entity.TeamEntity;
import com.example.twsServer.entity.UserEntity;
import com.example.twsServer.exception.ValidationException;
import com.example.twsServer.repository.MyTeamRepository;
import com.example.twsServer.repository.TeamRepository;
import com.example.twsServer.repository.UserRepository;
import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
        List<Tuple> tuples = myTeamRepository.findByUserId(userId);
        List<TeamDto> teamDtos = new ArrayList<>();

        for (Tuple tuple : tuples) {
            TeamDto teamDto = new TeamDto();
            teamDto.setTeamNo(tuple.get("teamNo", Integer.class));
            teamDto.setTeamName(tuple.get("teamName", String.class));
            teamDto.setPlace(tuple.get("place", String.class));
            teamDto.setSportsKind(tuple.get("sportsKind", String.class));
            teamDtos.add(teamDto);
        }
        return teamDtos;
    }

    // MyTeam 추가
    public boolean createMyTeam(String userId, Integer teamNo) {
        // Check valid teamNo
        TeamEntity team = teamRepository.findByTeamNo(teamNo);
        if (team == null) {
            System.out.printf("Invalid TeamNo(%d)\n", teamNo);
            return false;
        }

        MyTeamEntity myTeam = new MyTeamEntity();
        MyTeamId id = new MyTeamId(userId, teamNo);
        myTeam.setId(id);
        myTeam.setRegDate(new Date());

        try {
            myTeamRepository.save(myTeam);
            return true;
        } catch (Exception e) {
            throw new ValidationException(String.format("Invalid Input values(userId: %s, teamNo: %d) : %s", userId, teamNo, e.getMessage()));
        }
    }

    // MyTeam 삭제
    @Transactional
    public boolean deleteMyTeam(String userId, Integer teamNo) {
        try {
            myTeamRepository.deleteByIdUserIdAndIdTeamNo(userId, teamNo);
            return true;
        } catch (EmptyResultDataAccessException e) {
            System.out.printf("MyTeam not found for userId: %s, teamNo: %d\n", userId, teamNo);
            return false;
        } catch (Exception e) {
            System.out.printf("Failed to delete MyTeam for userId: %s, teamNo: %d. Reason: %s\n", userId, teamNo, e.getMessage());
            return false;
        }
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
