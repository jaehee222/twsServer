package com.example.twsServer.repository;

import com.example.twsServer.dto.TeamDto;
import com.example.twsServer.dto.TicketDto;
import com.example.twsServer.entity.TicketEntity;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TicketRepository extends JpaRepository<TicketEntity, Long> {

    List<TicketEntity> findByUserId(String userId);

    List<TicketEntity> findByUserIdAndGameDate(String userId, LocalDate gameDate);

    List<TicketEntity> findByTicketNo(int ticketNo);

    void deleteByTicketNo(int ticketNo);
}
