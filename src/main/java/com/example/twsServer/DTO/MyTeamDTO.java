package com.example.twsServer.DTO;

import java.util.Date;

public class MyTeamDTO {
    private String UserId;
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
