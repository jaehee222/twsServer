package com.example.twsServer.controller;

import com.example.twsServer.dto.TicketDto;
import com.example.twsServer.entity.TicketEntity;
import com.example.twsServer.exception.ValidationException;
import com.example.twsServer.service.TicketService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    private final TicketService ticketService;
    private final Cloudinary cloudinary;

    // Cloudinary 설정을 주입받음
    @Autowired
    public TicketController(TicketService ticketService,
                            @Value("${cloudinary.cloud_name}") String cloudName,
                            @Value("${cloudinary.api_key}") String apiKey,
                            @Value("${cloudinary.api_secret}") String apiSecret) {
        this.ticketService = ticketService;
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudName,
                "api_key", apiKey,
                "api_secret", apiSecret
        ));
    }

    @PostMapping("/newEntry")
    public ResponseEntity<Object> newEntry(HttpSession session,
                                           @RequestBody TicketDto ticketDto) {

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

        String existingTicket = "";
        if (ticketDto.getPhoto() != null) {
            try {
                // 기존 이미지 삭제 (수정 시)
                if (ticketDto.getTicketNo() != null) {
                    String deleteImgResult = deleteImg(userId, ticketDto);
                    if (!deleteImgResult.equals("success")) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(deleteImgResult);
                    }
                }

                // 업로드 진행
                byte[] imageBytes = Base64.getDecoder().decode(ticketDto.getPhoto());
                Map<String, Object> uploadResult = cloudinary.uploader().upload(imageBytes, ObjectUtils.asMap(
                        "resource_type", "image",  // 이미지 타입 명시
                        "invalidate", true
                ));

                // 업로드 성공 시 public_id 저장
                if (uploadResult.containsKey("public_id")) {
                    String publicId = (String) uploadResult.get("public_id");
                    ticketDto.setPhoto(publicId);
                } else {
                    throw new RuntimeException("Cloudinary 업로드 실패: public_id 없음");
                }

            } catch (Exception e) {
                e.printStackTrace();
                ticketDto.setPhoto(null);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이미지 업로드 실패: " + e.getMessage());
            }
        }

        // 티켓 작성 로직
        boolean isNewTicket = ticketService.newEntry(userId, ticketDto);

        if (isNewTicket) {
            return ResponseEntity.status(HttpStatus.CREATED).body("ticket create success");
        } else {
            return ResponseEntity.badRequest().body("ticket create fail");
        }
    }

    // 이미지를 표시할 때 URL을 동적으로 생성
    @GetMapping("/image/{publicId}")
    public ResponseEntity<Object> getImageUrl(@PathVariable String publicId) {
        String imageUrl = cloudinary.url().secure(true).generate(publicId); // URL 생성
        return ResponseEntity.ok().body(Map.of("imageUrl", imageUrl));
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
    public ResponseEntity<Object> deleteEntry(HttpSession session, @RequestBody TicketDto ticketDto) throws IOException {
        String userId = (String) session.getAttribute("userId");

        if (userId == null) {
            return ResponseEntity.badRequest().body("userId is null");
        }

        // 이미지 삭제 결과 확인
        String deleteImgResult = deleteImg(userId, ticketDto);
        if (!deleteImgResult.equals("success")) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(deleteImgResult);
        }

        boolean isDelTicket = ticketService.deleteEntry(userId, ticketDto);

        if (isDelTicket) {
            return ResponseEntity.status(HttpStatus.CREATED).body("ticket delete success");
        } else {
            return ResponseEntity.badRequest().body("ticket delete fail");
        }
    }

    public String deleteImg(String userId, TicketDto ticketDto) {
        ticketDto.setSearchCriteria("Detail");
        List<TicketDto> ticketList = ticketService.postView(userId, ticketDto);

        if (ticketList == null || ticketList.isEmpty()) {
            return "Ticket not found";
        }

        TicketDto isDelTicketTmp = ticketList.get(0);

        if (isDelTicketTmp.getPhoto() != null) {
            try {
                String publicId = isDelTicketTmp.getPhoto();

                // Cloudinary에서 이미지 삭제 (리소스 타입 및 캐시 무효화 추가)
                Map<String, Object> deleteResult = cloudinary.uploader().destroy(publicId, ObjectUtils.asMap(
                        "invalidate", true          // 캐시 무효화
                ));

                // 삭제 결과 로그 확인
                System.out.println("삭제 시도 public_id: " + publicId);
                System.out.println("Cloudinary delete result: " + deleteResult);

                if ("ok".equals(deleteResult.get("result"))) {
                    return "success";
                } else {
                    return "Cloudinary 이미지 삭제 실패: " + deleteResult.get("result");
                }

            } catch (Exception e) {
                e.printStackTrace();
                return "Cloudinary 이미지 삭제 중 예외 발생: " + e.getMessage();
            }
        }
        return "success";
    }
}
