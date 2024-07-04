package com.example.twsServer.repository;

import com.example.twsServer.entity.TicketEntity;
import com.example.twsServer.entity.TicketId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<TicketEntity, TicketId> {

}
