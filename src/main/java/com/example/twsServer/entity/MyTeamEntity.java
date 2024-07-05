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
//    private String teamName;
//    private String sportsKind;
//    private String place;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teamNo", referencedColumnName = "teamNo")
    TeamEntity teamEntity;

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

//    public String getSportsKind() {
//        return sportsKind;
//    }
//
//    public void setSportsKind(String sportsKind) {
//        this.sportsKind = sportsKind;
//    }
//
//    public String getTeamName() {
//        return teamName;
//    }
//
//    public void setTeamName(String teamName) {
//        this.teamName = teamName;
//    }
//
//    public int getTeamNo() {
//        return teamNo;
//    }
//
//    public void setTeamNo(int teamNo) {
//        this.teamNo = teamNo;
//    }
//
//    public String getPlace() {
//        return place;
//    }
//
//    public void setPlace(String place) {
//        this.place = place;
//    }
}
