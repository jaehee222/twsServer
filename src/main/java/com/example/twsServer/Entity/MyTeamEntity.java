package com.example.twsServer.entity;

import jakarta.persistence.Entity;

import java.util.Date;

@Entity
public class MyTeamEntity {
    private String userId;
    private int teamNo;
    private Date regDate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getTeamNo() {
        return teamNo;
    }

    public void setTeamNo(int teamNo) {
        this.teamNo = teamNo;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
}
