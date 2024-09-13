package com.example.twsServer.dto;

import java.util.Date;

public class TeamDto {
    private Integer teamNo;
    private String teamName;
    private String sportsKind;
    private String place;
    private Date regDate;

    // Rate
    private double homeRate;
    private double awayRate;
    private double totalRate;

    // Rate optional
    private Integer homeCnt;
    private Integer awayCnt;
    private Integer days;

    public TeamDto() {

    }

    public TeamDto(Integer teamNo, String teamName, String sportsKind, double homeRate, double awayRate, double totalRate, Integer homeCnt, Integer awayCnt, Integer days) {
        this.teamNo = teamNo;
        this.teamName = teamName;
        this.sportsKind = sportsKind;
        this.homeRate = homeRate;
        this.awayRate = awayRate;
        this.totalRate = totalRate;
        this.homeCnt = homeCnt;
        this.awayCnt = awayCnt;
        this.days = days;
    }

    public Integer getTeamNo() {
        return teamNo;
    }

    public void setTeamNo(Integer teamNo) {
        this.teamNo = teamNo;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setSportsKind(String sportsKind) {
        this.sportsKind = sportsKind;
    }

    public String getSportsKind() {
        return sportsKind;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPlace() {
        return place;
    }

    public double getHomeRate() {
        return homeRate;
    }

    public void setHomeRate(double homeRate) {
        this.homeRate = homeRate;
    }

    public double getAwayRate() {
        return awayRate;
    }

    public void setAwayRate(double awayRate) {
        this.awayRate = awayRate;
    }

    public double getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(double totalRate) {
        this.totalRate = totalRate;
    }

    public Date getRegDate() {
        return regDate;
    }

    public Integer getHomeCnt() {
        return homeCnt;
    }

    public Integer getAwayCnt() {
        return awayCnt;
    }

    public Integer getDays() {
        return days;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public void setHomeCnt(Integer homeCnt) {
        this.homeCnt = homeCnt;
    }

    public void setAwayCnt(Integer awayCnt) {
        this.awayCnt = awayCnt;
    }

    public void setDays(Integer days) {
        this.days = days;
    }
}
