package com.example.twsServer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MyTeamId implements Serializable {
    @Column(name = "user_id")
    String userId;
    @Column(name = "team_no")
    Integer teamNo;

    public MyTeamId() {

    }

    public MyTeamId(String userId, Integer teamNo) {
        this.userId = userId;
        this.teamNo = teamNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getTeamNo() {
        return teamNo;
    }

    public void setTeamNo(Integer teamNo) {
        this.teamNo = teamNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyTeamId myTeamId = (MyTeamId) o;
        return userId.equals(myTeamId.userId) && teamNo.equals(myTeamId.teamNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, teamNo);
    }
}
