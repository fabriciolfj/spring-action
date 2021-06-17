package com.github.fabriciolfj.springclient.controller;

import com.github.fabriciolfj.springclient.exception.UnknownIngredientException;
import com.github.fabriciolfj.springclient.model.Ingredient;
import com.github.fabriciolfj.springclient.model.Taco;
import com.github.fabriciolfj.springclient.model.Teste;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Set;

@RestController
@RequestMapping("/v2/test")
public class ClientReactiveController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientReactiveController.class);

    @Autowired
    private WebClient webClient;

    //o exchange retorna dados de cookie e cabeçalho
    @GetMapping("/outra")
    public void getExample4() {
        final String id = "CHED";
        Mono<Teste> ingredient = webClient
                .get().uri("/ingredients/{id}", id)
                .exchangeToMono(c -> {
                    LOGGER.info("Headers: {}", c.headers());
                    Teste teste = Teste.builder()
                            .value(c.headers().toString()).build();
                    //c.bodyToMono(Ingredient.class)
                    return Mono.just(teste);
                }).log();

        ingredient.subscribe(i -> LOGGER.info("Retorno ingredient: {}", i.getValue()));
    }

    @GetMapping
    public void getExample() {
        final String id = "CHED";
        Mono<Ingredient> ingredient = webClient
                .get().uri("/ingredients/{id}", id)
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError, e -> Mono.just(new UnknownIngredientException("Fail : " + e)))
                .bodyToMono(Ingredient.class);

        ingredient.subscribe(i -> LOGGER.info("Retorno ingredient: {}", i));
    }

    @GetMapping("/list")
    public void getExample2() {
        Flux<Ingredient> ingredientFlux = webClient
                .get()
                .uri("/ingredients")
                .retrieve()
                .bodyToFlux(Ingredient.class);

        ingredientFlux
                .timeout(Duration.ofSeconds(1)).subscribe(
                        i -> LOGGER.info("List: {}", i),
                        e -> LOGGER.error(e.getMessage()),
                        () -> LOGGER.info("complete"));
    }

    @DeleteMapping
    public void deleteExample() {
        webClient
                .delete()
                .uri("/ingredients/{id}", "SLSA")
                .retrieve()
                .bodyToMono(Void.class)
                .subscribe();
    }

    @PutMapping
    public void putExample() {
        Mono<Ingredient> ingredientMono = Mono.just(new Ingredient("SLSA", "atualizado", "nao sei"));
        Mono<Ingredient> retorno = webClient
                .put()
                .uri("/ingredients/{id}", "SLSA")
                .body(ingredientMono, Ingredient.class)
                .retrieve()
                .bodyToMono(Ingredient.class);

        retorno.subscribe(i -> LOGGER.info("Ingredient updated : {}", i));
    }

    @PostMapping
    public void postExample() {
        Mono<Taco> tacoMono = Mono.just(Taco.builder().name("teste2").ingredientIds(Set.of("FLTO")).build());
        Mono<Taco> result = webClient
                .post()
                .uri("/designtaco")
                .body(tacoMono, Taco.class)
                .retrieve()
                .bodyToMono(Taco.class);

        result.subscribe(r -> LOGGER.info("POST result: {}", r));
    }
}
