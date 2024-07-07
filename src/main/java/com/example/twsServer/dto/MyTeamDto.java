package com.example.twsServer.dto;

import java.util.Date;

public class MyTeamDto {
    private String userId;
    private Date regDate;
    private Integer teamNo;
    private String teamName;
    private String place;
    private String sportsKind;

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

    public Integer getTeamNo() {
        return teamNo;
    }

    public void setTeamNo(Integer teamNo) {
        this.teamNo = teamNo;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getSportsKind() {
        return sportsKind;
    }

    public void setSportsKind(String sportsKind) {
        this.sportsKind = sportsKind;
    }
}

