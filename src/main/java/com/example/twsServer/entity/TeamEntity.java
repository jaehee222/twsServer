package com.example.twsServer.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "Team")
public class TeamEntity {

    @Id
    private int teamNo;
    private String teamName;
    private String sportsKind;
    private String place;

    @OneToMany(mappedBy = "teamEntity")
    private Set<MyTeamEntity> myTeamEntity;

    public int getTeamNo() {
        return teamNo;
    }

    public void setTeamNo(int teamNo) {
        this.teamNo = teamNo;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getSportsKind() {
        return sportsKind;
    }

    public void setSportsKind(String sportsKind) {
        this.sportsKind = sportsKind;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Set<MyTeamEntity> getMyTeamEntity() {
        return myTeamEntity;
    }

    public void setMyTeamEntity(Set<MyTeamEntity> myTeamEntity) {
        this.myTeamEntity = myTeamEntity;
    }
}
