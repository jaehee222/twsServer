package com.example.twsServer.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Ticket")
public class TicketEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ticketNo;
    private String ticketName;
    private Integer homeTeamNo;
    private Integer awayTeamNo;
    private LocalDate gameDate;
    private Integer homeScore;
    private Integer awayScore;
    private String result;
    private String seat;
    private String photo;
    private Integer price;
    private String userId;
    private String ticketContent;

    // getters and setters
    public Integer getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(Integer ticketNo) {
        this.ticketNo = ticketNo;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public Integer getHomeTeamNo() {
        return homeTeamNo;
    }

    public void setHomeTeamNo(Integer homeTeamNo) {
        this.homeTeamNo = homeTeamNo;
    }

    public Integer getAwayTeamNo() {
        return awayTeamNo;
    }

    public void setAwayTeamNo(Integer awayTeamNo) {
        this.awayTeamNo = awayTeamNo;
    }

    public LocalDate getGameDate() {
        return gameDate;
    }

    public void setGameDate(LocalDate gameDate) {
        this.gameDate = gameDate;
    }

    public Integer getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(Integer homeScore) {
        this.homeScore = homeScore;
    }

    public Integer getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(Integer awayScore) {
        this.awayScore = awayScore;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
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
