package com.example.twsServer.repository;

import com.example.twsServer.entity.MyTeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyTeamRepository extends JpaRepository<MyTeamEntity, Long> {

    MyTeamEntity findByUserId(String userId);

    List<MyTeamEntity> getMyTeams(String userId);
}
