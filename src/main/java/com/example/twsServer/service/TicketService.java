package com.example.twsServer.service;

import com.example.twsServer.dto.TicketDto;
import com.example.twsServer.entity.TicketEntity;
import com.example.twsServer.exception.ValidationException;
import com.example.twsServer.repository.TeamRepository;
import com.example.twsServer.repository.TicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final TeamRepository teamRepository;

    public TicketService(TicketRepository ticketRepository, TeamRepository teamRepository) {
        this.ticketRepository = ticketRepository;
        this.teamRepository = teamRepository;
    }

    // 쿼리에 ticketNo값 넣으면 수정, 없으면 추가되는 로직..
    public boolean newEntry(String userId, TicketDto ticketDto) {
        try {
            TicketEntity ticket = null;
            // 티켓 번호로 기존 티켓 조회
            if (ticketDto.getTicketNo() != 0) {
                List<TicketEntity> existingTickets = ticketRepository.findByUserIdAndTicketNo(userId, ticketDto.getTicketNo());
                if (!existingTickets.isEmpty()) {
                    // 리스트에서 첫 번째 티켓을 가져옴 (단일 티켓을 가져오기 위해)
                    ticket = existingTickets.get(0);
                } else {
                    // 새 티켓 생성
                    throw new ValidationException("사용자가 가지고 있는 티켓이 아닙니다.");
                }
            } else {
                // 새 티켓 생성
                ticket = new TicketEntity();
            }

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
        } catch (Exception e) {
            throw new ValidationException("exceptionError:" + e.getMessage());
        }
    }

    public List<TicketDto> postView(String userId, TicketDto ticketDto) {
        List<TicketDto> resultDto = new ArrayList<>();

        try {
            var searchCriteria = ticketDto.getSearchCriteria();
            List<TicketEntity> ticketEntity = new ArrayList<>();

            // 사용자에 해당하는 전체 글목록
            if ("All".equals(searchCriteria)) {
                ticketEntity = ticketRepository.findByUserId(userId);
                // 조회날짜에 해당하는 글목록 (달력)
            } else if ("Date".equals(searchCriteria)) {
                ticketEntity = ticketRepository.findByUserIdAndGameDate(userId, ticketDto.getGameDate());
                // 티켓명에 해당하는 글목록 (글 상세보기)
            } else if ("Detail".equals(searchCriteria)) {
                ticketEntity = ticketRepository.findByUserIdAndTicketNo(userId, ticketDto.getTicketNo());
                if (ticketEntity.size() > 1) {
                    throw new ValidationException("DB error! 하나이상의 결과 출력");
                }
            } else {
                throw new ValidationException("searchCriteria invaild");
            }

            resultDto = convertToDto(ticketEntity);

        } catch (Exception e) {
            throw new ValidationException("exceptionError:" + e.getMessage());
        }
        return resultDto;
    }

    @Transactional
    public boolean deleteEntry(String userId, TicketDto ticketDto) {
        try {
            ticketRepository.deleteByUserIdAndTicketNo(userId, ticketDto.getTicketNo());
            return true; // 삭제 성공
        } catch (EmptyResultDataAccessException e) {
            return false; // 삭제할 엔티티가 없음
        }
    }

    public List<TicketDto> convertToDto(List<TicketEntity> ticketEntities) {
        return ticketEntities.stream().map(ticketEntity -> {
            TicketDto ticketDto = new TicketDto();

            ticketDto.setTicketNo(ticketEntity.getTicketNo());
            ticketDto.setTicketName(ticketEntity.getTicketName());
            ticketDto.setHomeTeamNo(ticketEntity.getHomeTeamNo());
            ticketDto.setAwayTeamNo(ticketEntity.getAwayTeamNo());
            ticketDto.setGameDate(ticketEntity.getGameDate());
            ticketDto.setHomeScore(ticketEntity.getHomeScore());
            ticketDto.setAwayScore(ticketEntity.getAwayScore());
            ticketDto.setResult(ticketEntity.getResult());
            ticketDto.setSeat(ticketEntity.getSeat());
            ticketDto.setPhoto(ticketEntity.getPhoto());
            ticketDto.setPrice(ticketEntity.getPrice());
            ticketDto.setUserId(ticketEntity.getUserId());
            ticketDto.setTicketContent(ticketEntity.getTicketContent());

            return ticketDto;
        }).collect(Collectors.toList());
    }
}
