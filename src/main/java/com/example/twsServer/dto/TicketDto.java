package com.example.twsServer.dto;

import java.time.LocalDate;

public class TicketDto {

    private int ticketNo;
    private String ticketName;
    private int homeTeamNo;
    private int awayTeamNo;
    private LocalDate gameDate;
    private int homeScore;
    private int awayScore;
    private char result;
    private String seat;
    private String photo;
    private int price;
    private String userId;
    private String ticketContent;

    // 게시물 조회 기준
    private String searchCriteria;

    public int getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(int ticketNo) {
        this.ticketNo = ticketNo;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public int getHomeTeamNo() {
        return homeTeamNo;
    }

    public void setHomeTeamNo(int homeTeamNo) {
        this.homeTeamNo = homeTeamNo;
    }

    public int getAwayTeamNo() {
        return awayTeamNo;
    }

    public void setAwayTeamNo(int awayTeamNo) {
        this.awayTeamNo = awayTeamNo;
    }

    public LocalDate getGameDate() {
        return gameDate;
    }

    public void setGameDate(LocalDate gameDate) {
        this.gameDate = gameDate;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public char getResult() {
        return result;
    }

    public void setResult(char result) {
        this.result = result;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTicketContent() {
        return ticketContent;
    }

    public void setTicketContent(String ticketContent) {
        this.ticketContent = ticketContent;
    }

    public String getSearchCriteria() {
        return searchCriteria;
    }

    public void setSearchCriteria(String searchCriteria) {
        this.searchCriteria = searchCriteria;
    }
}
