package com.example.twsServer.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "Ticket")
public class TicketEntity {

    @EmbeddedId
    private TicketId ticketId;

    private int homeTeamNo;
    private int awayTeamNo;
    private Date gameDate;
    private int homeScore;
    private int awayScore;
    private char result;
    private String seat;
    private String photo;
    private int price;
    private String userId;
    private String ticketContent;

    // getters and setters
    public TicketId getTicketId() {
        return ticketId;
    }

    public void setTicketId(TicketId ticketId) {
        this.ticketId = ticketId;
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

    public Date getGameDate() {
        return gameDate;
    }

    public void setGameDate(Date gameDate) {
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
}
