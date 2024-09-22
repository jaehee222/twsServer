package com.example.twsServer.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "MyTeam")
public class MyTeamEntity {
    @EmbeddedId
    private MyTeamId id;

    @Column(name = "reg_date")
    private LocalDate regDate;

    public MyTeamId getId() {
        return id;
    }

    public void setId(MyTeamId id) {
        this.id = id;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }
}
