package com.example.twsServer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.repository.cdi.Eager;

@Entity
public class Team {

    @Id
    private int TeamNo;
    private String TeamName;
    private String SportsKind;
    private String Place;

    public int getTeamNo() {
        return TeamNo;
    }

    public void setTeamNo(int teamNo) {
        TeamNo = teamNo;
    }

    public String getTeamName() {
        return TeamName;
    }

    public void setTeamName(String teamName) {
        TeamName = teamName;
    }

    public String getSportsKind() {
        return SportsKind;
    }

    public void setSportsKind(String sportsKind) {
        SportsKind = sportsKind;
    }

    public String getPlace() {
        return Place;
    }

    public void setPlace(String place) {
        Place = place;
    }
}
