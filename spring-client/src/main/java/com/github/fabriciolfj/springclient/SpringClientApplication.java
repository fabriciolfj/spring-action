package com.github.fabriciolfj.springclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class SpringClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringClientApplication.class, args);
	}

	@Bean
	public WebClient webClient() {
		return WebClient.create("http://localhost:8080");
	}

}
