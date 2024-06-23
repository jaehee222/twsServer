package com.example.twsServer.repository;

import com.example.twsServer.Entity.MyTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyTeamRepository extends JpaRepository<MyTeam,Long> {
}
