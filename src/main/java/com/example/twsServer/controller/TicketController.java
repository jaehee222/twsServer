package com.example.twsServer.controller;

import com.example.twsServer.dto.TicketDto;
import com.example.twsServer.entity.TicketEntity;
import com.example.twsServer.service.TicketService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService){
        this.ticketService = ticketService;
    }

    @PostMapping("/newEntry")
    public ResponseEntity<Object> newEntry(HttpSession session, TicketDto ticketDto){

        // 미/오입력 오류 추가예정..

        boolean isTicketEntry = ticketService.newEntry(ticketDto);

        if (isTicketEntry){
            return ResponseEntity.status(HttpStatus.CREATED).body("ticket create success");
        }else{
            return ResponseEntity.badRequest().body("ticket create fail");
        }
    }
}
