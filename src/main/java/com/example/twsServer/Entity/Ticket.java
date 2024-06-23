package com.example.twsServer.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Ticket {

    @Id
    private int TicketNo;
    private String TicketName;
    private int HomeTeamNo;
    private int AwayTeamNo;
    private Date GameDate;
    private int HomeScore;
    private int AwayScore;
    private char Result;
    private String Seat;
    private String Photo;
    private int Price;
    private String UserId;
    private String TicketContent;

    public int getTicketNo() {
        return TicketNo;
    }

    public void setTicketNo(int ticketNo) {
        TicketNo = ticketNo;
    }

    public String getTicketName() {
        return TicketName;
    }

    public void setTicketName(String ticketName) {
        TicketName = ticketName;
    }

    public int getHomeTeamNo() {
        return HomeTeamNo;
    }

    public void setHomeTeamNo(int homeTeamNo) {
        HomeTeamNo = homeTeamNo;
    }

    public int getAwayTeamNo() {
        return AwayTeamNo;
    }

    public void setAwayTeamNo(int awayTeamNo) {
        AwayTeamNo = awayTeamNo;
    }

    public Date getGameDate() {
        return GameDate;
    }

    public void setGameDate(Date gameDate) {
        GameDate = gameDate;
    }

    public int getHomeScore() {
        return HomeScore;
    }

    public void setHomeScore(int homeScore) {
        HomeScore = homeScore;
    }

    public int getAwayScore() {
        return AwayScore;
    }

    public void setAwayScore(int awayScore) {
        AwayScore = awayScore;
    }

    public char getResult() {
        return Result;
    }

    public void setResult(char result) {
        Result = result;
    }

    public String getSeat() {
        return Seat;
    }

    public void setSeat(String seat) {
        Seat = seat;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getTicketContent() {
        return TicketContent;
    }

    public void setTicketContent(String ticketContent) {
        TicketContent = ticketContent;
    }
}
