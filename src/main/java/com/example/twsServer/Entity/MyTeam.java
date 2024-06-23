package com.example.twsServer.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class MyTeam {

    @Id
    private String UserId;
    @Id
    private int TeamNo;
    private Date RegDate;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public int getTeamNo() {
        return TeamNo;
    }

    public void setTeamNo(int teamNo) {
        TeamNo = teamNo;
    }

    public Date getRegDate() {
        return RegDate;
    }

    public void setRegDate(Date regDate) {
        RegDate = regDate;
    }
}
