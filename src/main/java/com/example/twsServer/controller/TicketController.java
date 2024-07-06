package com.example.twsServer.controller;

import com.example.twsServer.dto.TicketDto;
import com.example.twsServer.entity.TicketEntity;
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

        // 미/오입력 오류 추가예정..

        boolean isTicketEntry = ticketService.newEntry(userId, ticketDto);

        if (isTicketEntry) {
            return ResponseEntity.status(HttpStatus.CREATED).body("ticket create success");
        } else {
            return ResponseEntity.badRequest().body("ticket create fail");
        }
    }

    @PostMapping("/postView")
    public ResponseEntity<?> postView(HttpSession session, @RequestBody TicketDto ticketDto) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            // 세션에 userId가 없을 때, ResponseEntity를 사용하여 오류 응답을 반환
            return ResponseEntity.badRequest().body("userId is null");
        }
        try {
            List<TicketDto> result = ticketService.postView(userId, ticketDto);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            // 예외가 발생한 경우, 클라이언트에게 오류 응답을 반환한다.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to process ticket: " + e.getMessage());
        }
    }
}
