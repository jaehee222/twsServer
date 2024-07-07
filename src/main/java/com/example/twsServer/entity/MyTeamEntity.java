package com.example.twsServer.entity;

import com.example.twsServer.dto.MyTeamDto;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "MyTeam")
public class MyTeamEntity {
    @Id
    private String userId;
    private Date regDate;
    private Integer teamNo;

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
}
