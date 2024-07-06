package com.example.twsServer.service;

import com.example.twsServer.dto.TicketDto;
import com.example.twsServer.entity.TicketEntity;
import com.example.twsServer.repository.TeamRepository;
import com.example.twsServer.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final TeamRepository teamRepository;

    public TicketService(TicketRepository ticketRepository, TeamRepository teamRepository) {
        this.ticketRepository = ticketRepository;
        this.teamRepository = teamRepository;
    }

    public boolean newEntry(String userId, TicketDto ticketDto) {

        TicketEntity ticket = new TicketEntity();

        String homeTeamName = teamRepository.findByTeamNo(ticketDto.getHomeTeamNo()).getTeamName();
        String awayTeamName = teamRepository.findByTeamNo(ticketDto.getAwayTeamNo()).getTeamName();

        // 티켓이름 자동셋팅
        ticket.setTicketName(homeTeamName + " VS " + awayTeamName);
        ticket.setHomeTeamNo(ticketDto.getHomeTeamNo());
        ticket.setAwayTeamNo(ticketDto.getAwayTeamNo());
        ticket.setGameDate(ticketDto.getGameDate());
        ticket.setHomeScore(ticketDto.getHomeScore());
        ticket.setAwayScore(ticketDto.getAwayScore());
        ticket.setResult(ticketDto.getResult());
        ticket.setSeat(ticketDto.getSeat());
        ticket.setPhoto(ticketDto.getPhoto());
        ticket.setPrice(ticketDto.getPrice());
        ticket.setUserId(userId);
        ticket.setTicketContent(ticketDto.getTicketContent());

        ticketRepository.save(ticket);

        return true;
    }

    public List<TicketDto> postView(String userId, TicketDto ticketDto) {
        var searchCriteria = ticketDto.getSearchCriteria();

        // 사용자에 해당하는 전체 글목록
        if ("all".equals(searchCriteria)){
           return ticketRepository.findByUserId(userId);
        // 조회날짜에 해당하는 글목록 (달력)
        }else if ("Date".equals(searchCriteria)){
            return ticketRepository.findByUserIdAndGameDate(userId, ticketDto.getGameDate());
        // 티켓명에 해당하는 글목록 (글 상세보기)
        }else if ("Detail".equals(searchCriteria)){
            return ticketRepository.findByTicketNo(ticketDto.getTicketNo());
        }else {
            throw new IllegalArgumentException("searchCriteria invaild");
        }
    }
}
