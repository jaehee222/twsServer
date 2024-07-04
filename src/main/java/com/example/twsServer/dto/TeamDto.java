package com.example.twsServer.dto;

public class TeamDto {
    private int teamNo;
    private String teamName;
    private String sportsKind;
    private String place;

    public void setTeamNo(int teamNo) {
        this.teamNo = teamNo;
    }

    public int getTeamNo() {
        return teamNo;
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
}
