package com.example.twsServer.service;

import com.example.twsServer.dto.TeamDto;
import com.example.twsServer.entity.MyTeamEntity;
import com.example.twsServer.entity.MyTeamId;
import com.example.twsServer.entity.TeamEntity;
import com.example.twsServer.exception.ValidationException;
import com.example.twsServer.repository.MyTeamRepository;
import com.example.twsServer.repository.TeamRepository;
import com.example.twsServer.repository.TicketRepository;
import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

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
    public boolean createMyTeam(String userId, int teamNo, LocalDate regDate) {
        MyTeamEntity myTeam = new MyTeamEntity();
        MyTeamId id = new MyTeamId(userId, teamNo);
        myTeam.setId(id);
        myTeam.setRegDate(regDate);

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
            HashMap<Result, Integer> homeMap = new HashMap<Result, Integer>() {{
                put(Result.WIN, 0);
                put(Result.TIE, 0);
                put(Result.LOSE, 0);
            }};
            HashMap<Result, Integer> awayMap = new HashMap<Result, Integer>() {{
                put(Result.WIN, 0);
                put(Result.TIE, 0);
                put(Result.LOSE, 0);
            }};

            List<Map<String, Object>> infoList = ticketRepository.findTicketsByUserIdAndTeamNo(userId, teamNo);

            String teamName = "";
            String sportsKind = "";
            String dateStr = "";
            int days = 0;
            LocalDate currentDate = LocalDate.now();

            for (Map<String, Object> row : infoList) {

                String type = row.get("type") != null ? row.get("type").toString() : "";
                Integer homeScore = row.get("homeScore") != null ? Integer.parseInt(row.get("homeScore").toString()) : 0;
                Integer awayScore = row.get("awayScore") != null ? Integer.parseInt(row.get("awayScore").toString()) : 0;
                String result = row.get("result") != null ? row.get("result").toString() : "";

                sportsKind = row.get("sportsKind") != null ? row.get("sportsKind").toString() : "";
                teamName = row.get("teamName") != null ? row.get("teamName").toString() : "";
                dateStr = row.get("regDate") != null ? row.get("regDate").toString() : "";

                if (!dateStr.isEmpty()) {
                    LocalDate regDate = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                    long daysBetween = ChronoUnit.DAYS.between(currentDate, regDate);
                    days = (int) daysBetween * -1;
                }

                if (result.isEmpty()) {
                    continue;
                } else {
                    if (type.equals("HOME")) {
                        SetHashMap(type, homeScore, awayScore, homeMap);
                    } else {
                        SetHashMap(type, homeScore, awayScore, awayMap);
                    }
                }
            }

            int homeWins = homeMap.get(Result.WIN);
            int homeTies = homeMap.get(Result.TIE);
            int homeLosses = homeMap.get(Result.LOSE);

            int awayWins = awayMap.get(Result.WIN);
            int awayTies = awayMap.get(Result.TIE);
            int awayLosses = awayMap.get(Result.LOSE);

            int homeTotalGames = homeWins + homeTies + homeLosses;
            int awayTotalGames = awayWins + awayTies + awayLosses;

            double homeWinRate = homeTotalGames > 0 ? (double) homeWins / homeTotalGames : 0.0;
            double homeTieRate = homeTotalGames > 0 ? (double) homeTies / homeTotalGames : 0.0;
            double homeLoseRate = homeTotalGames > 0 ? (double) homeLosses / homeTotalGames : 0.0;

            double awayWinRate = awayTotalGames > 0 ? (double) awayWins / awayTotalGames : 0.0;
            double awayTieRate = awayTotalGames > 0 ? (double) awayTies / awayTotalGames : 0.0;
            double awayLoseRate = awayTotalGames > 0 ? (double) awayLosses / awayTotalGames : 0.0;

            int totalWins = homeWins + awayWins;
            int totalTies = homeTies + awayTies;
            int totalLosses = homeLosses + awayLosses;

            int totalGames = homeTotalGames + awayTotalGames;

            double totalWinRate = totalGames > 0 ? (double) totalWins / totalGames : 0.0;
            double totalTieRate = totalGames > 0 ? (double) totalTies / totalGames : 0.0;
            double totalLoseRate = totalGames > 0 ? (double) totalLosses / totalGames : 0.0;

            TeamDto teamDto = new TeamDto();
            teamDto.setTeamNo(teamNo);
            teamDto.setTeamName(teamName);
            teamDto.setSportsKind(sportsKind);
            teamDto.setHomeRate(homeWinRate);
            teamDto.setAwayRate(awayWinRate);
            teamDto.setWinRate(totalWinRate);
            teamDto.setLoseRate(totalLoseRate);
            teamDto.setTieRate(totalTieRate);
            teamDto.setHomeCnt(homeTotalGames);
            teamDto.setAwayCnt(awayTotalGames);
            teamDto.setRegDate(dateStr);
            teamDto.setDays(days);

            return teamDto;
        } catch (Exception e) {
            throw new ValidationException("get myTeamRate Error");
        }
    }

    private void SetHashMap(String type, Integer homeScore, Integer awayScore, HashMap<Result, Integer> map) {
        switch (type) {
            case "HOME" : {
                if (homeScore > awayScore) {
                    Integer cnt = map.get(Result.WIN);
                    map.put(Result.WIN, ++cnt);
                } else if (homeScore < awayScore) {
                    Integer cnt = map.get(Result.LOSE);
                    map.put(Result.LOSE, ++cnt);
                } else {
                    Integer cnt = map.get(Result.TIE);
                    map.put(Result.TIE, ++cnt);
                }
                break;
            }
            case "AWAY" : {
                if (homeScore < awayScore) {
                    Integer cnt = map.get(Result.WIN);
                    map.put(Result.WIN, ++cnt);
                } else if (homeScore > awayScore) {
                    Integer cnt = map.get(Result.LOSE);
                    map.put(Result.LOSE, ++cnt);
                } else {
                    Integer cnt = map.get(Result.TIE);
                    map.put(Result.TIE, ++cnt);
                }
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
