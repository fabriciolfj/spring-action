package com.github.fabriciolfj.jmsclient;

import com.github.fabriciolfj.jmsclient.dto.TacoDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessagingMessageConverter;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class JmsClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(JmsClientApplication.class, args);
	}

	@Bean
	public MappingJackson2MessageConverter messageConverter() {
		final var converter = new MappingJackson2MessageConverter();
		converter.setTypeIdPropertyName("_typeId");

		Map<String, Class<?>> typeIdMappings = new HashMap<>();
		typeIdMappings.put("order", TacoDTO.class); //dando mais flexibilidade, ou seja, posso mapear para uma outra classe com nome order
		converter.setTypeIdMappings(typeIdMappings);
		return converter;
	}

}
