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

    @Query(value = "SELECT ticket_no, ticket_name, result, home_score, away_score, home_team_no, away_team_no, game_date, seat, photo, price, user_id, ticket_content FROM ticket WHERE user_id = :userId AND game_date BETWEEN :startDate AND :endDate"
            , nativeQuery = true)
    List<TicketEntity> findByUserIdAndMonth(String userId, LocalDate startDate, LocalDate endDate);

    List<TicketEntity> findByUserIdAndTicketNo(String userId, int ticketNo);

    Page<TicketEntity> findByUserId(String userId, Pageable pageable);

    @Query(value = "SELECT 'HOME' AS type, t.result, t.home_score, t.away_score, tm.team_name, tm.sports_kind, myt.reg_date FROM team tm, my_team myt LEFT OUTER JOIN ticket t ON t.user_id = myt.user_id AND t.home_team_no = myt.team_no WHERE myt.user_id = :userId AND myt.team_no = :teamNo AND myt.team_no = tm.team_no " +
            "UNION ALL SELECT 'AWAY' AS type, t.result, t.home_score, t.away_score, tm.team_name, tm.sports_kind, myt.reg_date FROM team tm, my_team myt LEFT OUTER JOIN ticket t ON t.user_id = myt.user_id AND t.away_team_no = myt.team_no WHERE myt.user_id = :userId AND myt.team_no = :teamNo AND myt.team_no = tm.team_no"
            , nativeQuery = true)
    List<Map<String, Object>> findTicketsByUserIdAndTeamNo(@Param("userId") String userId, @Param("teamNo") int teamNo);

    void deleteByUserIdAndTicketNo(String userId, int ticketNo);
}
