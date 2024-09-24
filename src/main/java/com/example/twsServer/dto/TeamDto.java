package com.example.twsServer.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TeamDto {
    private Integer teamNo;
    private String teamName;
    private String sportsKind;
    private String place;
    private LocalDate regDate;

    // Rate
    private double homeRate;
    private double awayRate;
    private double winRate;
    private double loseRate;
    private double tieRate;

    // Rate optional
    private Integer homeCnt;
    private Integer awayCnt;
    private Integer days;

    public TeamDto() {

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

    public double getWinRate() {
        return winRate;
    }

    public double getLoseRate() {
        return loseRate;
    }

    public double getTieRate() {
        return tieRate;
    }

    public void setWinRate(double winRate) {
        this.winRate = winRate;
    }

    public void setLoseRate(double loseRate) {
        this.loseRate = loseRate;
    }

    public void setTieRate(double tieRate) {
        this.tieRate = tieRate;
    }

    public LocalDate getRegDate() {
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

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public void setRegDate(String regDateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.regDate = LocalDate.parse(regDateStr, formatter);
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
