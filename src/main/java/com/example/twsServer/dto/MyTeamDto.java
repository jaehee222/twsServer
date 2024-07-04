package com.example.twsServer.dto;

import java.util.Date;
import java.util.List;

public class MyTeamDto {
    private String userId;
    private Date regDate;
//    private List<TeamDto> teamDtoList;

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

//    public List<TeamDto> getTeamDtoList() {
//        return teamDtoList;
//    }
//
//    public void setTeamDtoList(List<TeamDto> teamDtoList) {
//        this.teamDtoList = teamDtoList;
//    }
}
