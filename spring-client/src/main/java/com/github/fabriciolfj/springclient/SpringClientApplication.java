package com.github.fabriciolfj.springclient;

import com.github.fabriciolfj.springclient.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.net.URI;
import java.time.Instant;
import java.util.List;

@Slf4j
@SpringBootApplication
public class SpringClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringClientApplication.class, args);
	}

	@Bean
	public WebClient webClient() {
		return WebClient.create("http://localhost:8080");
	}

	@Bean
	public ApplicationRunner sender(final RSocketRequester.Builder builder) {
		return args -> {
			var tcp = builder.tcp("localhost", 7000);
			/*tcp.route("greeting")
					.data("Hello rsocket!")
					.retrieveMono(String.class)
					.subscribe(response -> log.info("Got a response: " + response));

			tcp.route("greeting/{name}", "fabricio")
					.data("Hello RSocket!")
					.retrieveMono(String.class)
					.subscribe(response -> log.info("Got a response: " + response));

			tcp.route("/stock/{symbol}", "SYS")
					.retrieveFlux(StockQuote.class)
					.subscribe(q -> log.info("Receive: {}", q.toString()));

			tcp.route("alert")
					.data(new Alert(Alert.Level.YELLOW, "Fabricio", Instant.now()))
					.send()
					.subscribe();*/

			/*var list = List.of(
					new GratuityIn(BigDecimal.valueOf(100), 10),
					new GratuityIn(BigDecimal.valueOf(56), 32),
					new GratuityIn(BigDecimal.valueOf(667), 51),
					new GratuityIn(BigDecimal.valueOf(674), 3)
					);
			var fluxIn = Flux.fromIterable(list);
			tcp.route("gratuity")
					.data(fluxIn)
					.retrieveFlux(GratuityOut.class)
					.subscribe(g -> log.info("Return: {}" + g.toString()));*/

			/*var list = List.of(
					new GratuityIn(BigDecimal.valueOf(100), 10),
					new GratuityIn(BigDecimal.valueOf(56), 32),
					new GratuityIn(BigDecimal.valueOf(667), 51),
					new GratuityIn(BigDecimal.valueOf(674), 3)
			);*/

			var list2 = List.of(new PersonIn("Fabricio"), new PersonIn("Suzana"));
			var fluxIn = Flux.fromIterable(list2);
			RSocketRequester requester = builder.websocket(URI.create("http://localhost:8080/rsocket"));
			requester.route("person")
					.data(fluxIn)
					.retrieveFlux(PersonOut.class)
					.subscribe(p -> log.info(p.toString()));
			/*requester.route("gratuity")
					.data(fluxIn)
					.retrieveFlux(GratuityOut.class)
					.subscribe(g -> log.info("Return: {}" + g.toString()));*/
		};

	}

}
