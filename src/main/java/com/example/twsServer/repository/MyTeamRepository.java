package com.example.twsServer.repository;

import com.example.twsServer.entity.MyTeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MyTeamRepository extends JpaRepository<MyTeamEntity, Long> {

    MyTeamEntity findByUserId(String userId);

    // userId값을 입력받아 DB만들 때에는 findByUserId 이런식으로 써야함
    // 내가 함수의 이름을 적어주고 싶을때는 Query를 직접 지정필요
    @Query("SELECT m FROM MyTeamEntity m WHERE m.userId = :userId")
    List<MyTeamEntity> getMyTeams(@Param("userId") String userId);
}
