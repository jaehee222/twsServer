package com.example.twsServer.service;

import com.example.twsServer.Entity.User;
import com.example.twsServer.repository.MyTeamRepository;
import com.example.twsServer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class MyTeamService {

    @Autowired
    private UserRepository userRepository;  // 로그인 세션때문에
    @Autowired
    private MyTeamRepository myTeamRepository;

    // 나의 팀 중복확인
    public boolean TeamNoDubleCheck(String id){
        if (userRepository.existsByUserId(id)) {
            return true;
        }else{
            return false;
        }
    }
}
