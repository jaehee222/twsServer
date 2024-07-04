package com.example.twsServer.repository;

import com.example.twsServer.entity.TicketEntity;
import com.example.twsServer.entity.TicketId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TicketRepository extends JpaRepository<TicketEntity, TicketId> {

    @Query("SELECT MAX(t.TicketId.ticketNo) FROM TicketEntity t")
    Integer findMaxTicketNo();
}
