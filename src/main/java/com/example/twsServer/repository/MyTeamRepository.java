package com.example.twsServer.repository;

import com.example.twsServer.Entity.MyTeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyTeamRepository extends JpaRepository<MyTeamEntity, Long> {
}
