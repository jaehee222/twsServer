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
    private int teamNo;

    @OneToOne
    @JoinColumn(name = "teamNo", insertable = false, updatable = false)
    private TeamEntity teamEntity;

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

    public int getTeamNo() {
        return teamNo;
    }

    public void setTeamNo(int teamNo) {
        this.teamNo = teamNo;
    }

    public TeamEntity getTeamEntity() {
        return teamEntity;
    }

    public void setTeamEntity(TeamEntity teamEntity) {
        this.teamEntity = teamEntity;
    }
}
