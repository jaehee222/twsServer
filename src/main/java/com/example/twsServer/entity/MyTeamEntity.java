package com.example.twsServer.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "MyTeam")
public class MyTeamEntity {
    @EmbeddedId
    private MyTeamId id;

    @Column(name = "reg_date")
    private Date regDate;

    public MyTeamId getId() {
        return id;
    }

    public void setId(MyTeamId id) {
        this.id = id;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
}
