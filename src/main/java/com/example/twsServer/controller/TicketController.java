package com.example.twsServer.controller;

import com.example.twsServer.dto.TicketDto;
import com.example.twsServer.exception.ValidationException;
import com.example.twsServer.service.TicketService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/newEntry")
    public ResponseEntity<Object> newEntry(HttpSession session, @RequestBody TicketDto ticketDto) {

        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.badRequest().body("userId is null");
        }
        if (ticketDto.getHomeTeamNo() == null) {
            return ResponseEntity.badRequest().body("homeTeamNo is null");
        }
        if (ticketDto.getAwayTeamNo() == null) {
            return ResponseEntity.badRequest().body("awayTeamNo is null");
        }
        if (ticketDto.getGameDate() == null) {
            return ResponseEntity.badRequest().body("gameDate is null");
        }
        if (ticketDto.getHomeScore() == null) {
            return ResponseEntity.badRequest().body("homeScore is null");
        }
        if (ticketDto.getAwayScore() == null) {
            return ResponseEntity.badRequest().body("awayScore is null");
        }
        if (ticketDto.getTicketContent() == null || ticketDto.getTicketContent().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("content is null");
        }

        boolean isNewTicket = ticketService.newEntry(userId, ticketDto);

        if (isNewTicket) {
            return ResponseEntity.status(HttpStatus.CREATED).body("ticket create success");
        } else {
            return ResponseEntity.badRequest().body("ticket create fail");
        }
    }

    @PostMapping("/postView")
    public ResponseEntity<Object> postView(HttpSession session, @RequestBody TicketDto ticketDto) {
        String userId = (String) session.getAttribute("userId");

        if (userId == null) {
            // 세션에 userId가 없을 때, ResponseEntity를 사용하여 오류 응답을 반환
            return ResponseEntity.badRequest().body("userId is null");
        }
        try {
            List<TicketDto> result = ticketService.postView(userId, ticketDto);
            return ResponseEntity.ok(result);
        } catch (ValidationException e) {
            throw e;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to process ticket: " + e.getMessage());
        }
    }

    @PostMapping("/deleteEntry")
    public ResponseEntity<Object> deleteEntry(HttpSession session, @RequestBody TicketDto ticketDto) {
        String userId = (String) session.getAttribute("userId");

        if (userId == null) {
            return ResponseEntity.badRequest().body("userId is null");
        }

        boolean isDelTicket = ticketService.deleteEntry(userId, ticketDto);

        if (isDelTicket) {
            return ResponseEntity.status(HttpStatus.CREATED).body("ticket delete success");
        } else {
            return ResponseEntity.badRequest().body("ticket delete fail");
        }
    }
}
