package com.example.twsServer.service;

import com.example.twsServer.entity.MyTeamEntity;
import com.example.twsServer.repository.MyTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyTeamService {
    private final MyTeamRepository myTeamRepository;

    @Autowired
    public MyTeamService(MyTeamRepository myTeamRepository) {
        this.myTeamRepository = myTeamRepository;
    }

    // MyTeam 조회
    public List<MyTeamEntity> findByUserId(String userId) {
        return myTeamRepository.findByUserId(userId);
    }
}
