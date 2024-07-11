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
import com.example.twsServer.repository.TicketRepository;
import com.example.twsServer.repository.UserRepository;
import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MyTeamService {
    private final MyTeamRepository myTeamRepository;
    private final TeamRepository teamRepository;
    private final TicketRepository ticketRepository;

    @Autowired
    public MyTeamService(MyTeamRepository myTeamRepository, TeamRepository teamRepository, TicketRepository ticketRepository) {
        this.myTeamRepository = myTeamRepository;
        this.teamRepository = teamRepository;
        this.ticketRepository = ticketRepository;
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
    public boolean createMyTeam(String userId, int teamNo) {
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
    public boolean deleteMyTeam(String userId, int teamNo) {
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

    // 승률 조회
    private enum Result {
        WIN, TIE, LOSE
    }
    public TeamDto getTeamRate(String userId, int teamNo) {

        try {
            HashMap<Result, Integer> homeCnt = new HashMap<Result, Integer>() {{
                put(Result.WIN, 0);
                put(Result.TIE, 0);
                put(Result.LOSE, 0);
            }};
            HashMap<Result, Integer> awayCnt = new HashMap<Result, Integer>() {{
                put(Result.WIN, 0);
                put(Result.TIE, 0);
                put(Result.LOSE, 0);
            }};

            List<Map<String, Object>> infoList = ticketRepository.findTicketsByUserIdAndTeamNo(userId, teamNo);

            System.out.println(" ##### 1 ##### ");

            String teamName = "";
            String sportsKind = "";
            for (Map<String, Object> row : infoList) {

                String type = (String) row.get("type");
                String result = (row.get("result")).toString();
                sportsKind = (String) row.get("sportsKind");
                teamName = (String) row.get("teamName");

                if (type.equals("HOME")) {
                    SetHashMap(result, homeCnt);
                } else {
                    SetHashMap(result, awayCnt);
                }
            }

            int homeTotal = homeCnt.get(Result.WIN) + homeCnt.get(Result.TIE) + homeCnt.get(Result.LOSE);
            int awayTotal = awayCnt.get(Result.WIN) + awayCnt.get(Result.TIE) + awayCnt.get(Result.LOSE);

            double homeRate = (double) homeCnt.get(Result.WIN) / homeTotal;
            double awayRate = (double) awayCnt.get(Result.WIN) / awayTotal;
            double totalRate = homeRate + awayRate;

            TeamDto teamDto;
            teamDto = new TeamDto(teamNo,teamName, sportsKind, homeRate, awayRate, totalRate);

            return teamDto;
        } catch (Exception e) {
            throw new ValidationException("get myTeamRate Error");
        }
    }

    private void SetHashMap(String result, HashMap<Result, Integer> map) {
        switch(result) {
            case "w" : {
                Integer cnt = map.get(Result.WIN);
                map.put(Result.WIN, ++cnt);
                break;
            }
            case "t" : {
                Integer cnt = map.get(Result.TIE);
                map.put(Result.TIE, ++cnt);
                break;
            }
            case "l" : {
                Integer cnt = map.get(Result.LOSE);
                map.put(Result.LOSE, ++cnt);
                break;
            }
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
