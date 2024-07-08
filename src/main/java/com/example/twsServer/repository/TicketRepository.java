package com.example.twsServer.repository;

import com.example.twsServer.entity.TicketEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface TicketRepository extends JpaRepository<TicketEntity, Long> {

    List<TicketEntity> findByUserId(String userId);

    List<TicketEntity> findByUserIdAndGameDate(String userId, LocalDate gameDate);

    List<TicketEntity> findByUserIdAndTicketNo(String userId, int ticketNo);

    Page<TicketEntity> findByUserId(String userId, Pageable pageable);

    @Query(value = "SELECT 'HOME' AS type, t.result AS result, t.home_score, t.away_score, tm.team_name AS teamName, tm.sports_kind AS sportsKind FROM team tm, ticket t WHERE t.user_id = :userId AND t.home_team_no = :teamNo AND t.home_team_no = tm.team_no " +
            "UNION ALL SELECT 'AWAY', t.result AS result, t.home_score, t.away_score, tm.team_name AS teamName, tm.sports_kind AS sportsKind FROM team tm, ticket t WHERE t.user_id = :userId AND t.away_team_no = :teamNo AND t.away_team_no = tm.team_no"
            , nativeQuery = true)
    List<Map<String, Object>> findTicketsByUserIdAndTeamNo(@Param("userId") String userId, @Param("teamNo") int teamNo);

    void deleteByUserIdAndTicketNo(String userId, int ticketNo);
}
