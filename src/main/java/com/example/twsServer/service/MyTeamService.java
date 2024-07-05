package com.example.twsServer.service;

import com.example.twsServer.dto.MyTeamDto;
import com.example.twsServer.entity.MyTeamEntity;
import com.example.twsServer.repository.MyTeamRepository;
import org.springframework.stereotype.Service;

@Service
public class MyTeamService {
    private MyTeamRepository myTeamRepository;

    // MyTeam 조회
    public MyTeamDto getMyTeam(String userId) {
        MyTeamEntity myTeamEntity = myTeamRepository.findByUserId(userId);

        return convertToDto(myTeamEntity);
    }

    private MyTeamDto convertToDto(MyTeamEntity myTeamEntity) {
        MyTeamDto myTeamDto = new MyTeamDto();
        myTeamDto.setUserId(myTeamEntity.getUserId());
        myTeamDto.setRegDate(myTeamEntity.getRegDate());
//        myTeamDto.setTeamNo(myTeamEntity.getTeamNo());
//        myTeamDto.setTeamName(myTeamEntity.getTeamName());
//        myTeamDto.setSportsKind(myTeamEntity.getSportsKind());
//        myTeamDto.setPlace(myTeamEntity.getPlace());

        return myTeamDto;
    }
}
