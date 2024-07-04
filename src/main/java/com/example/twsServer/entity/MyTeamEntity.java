package com.example.twsServer.entity;

import com.example.twsServer.dto.MyTeamDto;
import com.example.twsServer.dto.TeamDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;
import java.util.List;

@Entity
public class MyTeamEntity {
    @Id
    private String userId;
    private Date regDate;
    private List<TeamDto> myTeamDtoList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public List<TeamDto> getMyTeamDtoList() {
        return myTeamDtoList;
    }

    public void setMyTeamDtoList(List<TeamDto> myTeamDtoList) {
        this.myTeamDtoList = myTeamDtoList;
    }
}
