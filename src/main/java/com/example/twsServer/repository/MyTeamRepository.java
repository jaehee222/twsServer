package com.example.twsServer.repository;

import com.example.twsServer.entity.MyTeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyTeamRepository extends JpaRepository<MyTeamEntity, Long> {

    // userId값을 입력받아 DB만들 때에는 findByUserId 이런식으로 써야함
    // 내가 함수의 이름을 적어주고 싶을때는 Query를 직접 지정필요
    @Query("SELECT m FROM MyTeamEntity m join m.teamEntity t ON m.teamNo = t.teamNo WHERE m.userId = :userId")
    List<MyTeamEntity> findByUserId(String userId);
}
