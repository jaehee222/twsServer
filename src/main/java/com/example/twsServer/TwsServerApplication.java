package com.example.twsServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 임시 추가 (서버 확인용)
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
@SpringBootApplication(exclude = {
		DataSourceAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class
})
public class TwsServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwsServerApplication.class, args);
	}
}

//@SpringBootApplication
//public class TwsServerApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(TwsServerApplication.class, args);
//	}
//}
