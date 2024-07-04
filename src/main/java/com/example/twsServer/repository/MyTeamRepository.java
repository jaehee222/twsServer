package com.example.twsServer.repository;

import com.example.twsServer.entity.MyTeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyTeamRepository extends JpaRepository<MyTeamEntity, Long> {

    MyTeamEntity findByUserId(String userId);
}
