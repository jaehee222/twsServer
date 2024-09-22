package com.example.twsServer.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MyTeamDto {
    private String userId;
    private LocalDate regDate;
    private Integer teamNo;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public void setRegDate(String regDateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.regDate = LocalDate.parse(regDateStr, formatter);
    }

    public Integer getTeamNo() {
        return teamNo;
    }

    public void setTeamNo(Integer teamNo) {
        this.teamNo = teamNo;
    }
}

