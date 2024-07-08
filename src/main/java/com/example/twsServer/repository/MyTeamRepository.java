package com.example.twsServer.repository;

import com.example.twsServer.entity.MyTeamEntity;
import com.example.twsServer.entity.TicketEntity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyTeamRepository extends JpaRepository<MyTeamEntity, Long> {

    @Query(value = "SELECT t.team_no AS teamNo, t.team_name AS teamName, t.place AS place, t.sports_kind AS sportsKind " +
            "FROM my_team m, team t WHERE m.user_id = :userId AND t.team_no = m.team_no", nativeQuery = true)
    List<Tuple> findByUserId(@Param("userId") String userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM MyTeamEntity m WHERE m.id.userId = :userId AND m.id.teamNo = :teamNo")
    void deleteByIdUserIdAndIdTeamNo(@Param("userId") String userId, @Param("teamNo") int teamNo);
}
