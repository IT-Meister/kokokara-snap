package jp.co.itmeister.userservice.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@EnableJpaAuditing
@RestController
public class PrototypeApplication {
	public static void main(String[] args) {
		SpringApplication.run(PrototypeApplication.class, args);
	}

	@RequestMapping("/")
	public String hello() {
		return "Hello Spring Boot on Docker!";
	}
}
