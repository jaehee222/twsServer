package com.example.twsServer.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TicketId implements Serializable {
    private int ticketNo;
    private String ticketName;

    // 기본 생성자
    public TicketId() {}

    // 생성자
    public TicketId(int ticketNo, String ticketName) {
        this.ticketNo = ticketNo;
        this.ticketName = ticketName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketId ticketId = (TicketId) o;
        return ticketNo == ticketId.ticketNo && Objects.equals(ticketName, ticketId.ticketName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketNo, ticketName);
    }

    // getters and setters
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
}