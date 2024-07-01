package com.example.twsServer;

import com.example.twsServer.dto.UserDto;
import com.example.twsServer.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

@SpringBootTest
class TwsServerApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void testRegisterUser() throws Exception {
		// Mock 사용자 데이터 생성
		UserDto userDto = new UserDto();
		userDto.setUserId("master");
		userDto.setPassword("0000");
		userDto.setNickName("master");
		userDto.setEmail("master@example.com");
		userDto.setRegDate(new Date());

		// Mocking: userService.join 메서드가 호출될 때 반환할 값 설정
		Mockito.when(userService.join(Mockito.any(UserDto.class))).thenReturn(userDto);

		// POST 요청 보내기
		mockMvc.perform(MockMvcRequestBuilders.post("/user/join")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(userDto)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.userId").value("master"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.password").value("0000"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.nickName").value("master"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.email").value("master@example.com"));
	}

}
