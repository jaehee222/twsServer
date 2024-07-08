package com.example.twsServer.repository;

import com.example.twsServer.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeamRepository  extends JpaRepository<TeamEntity, Long> {

    TeamEntity findByTeamNo(int teamNo);
}
