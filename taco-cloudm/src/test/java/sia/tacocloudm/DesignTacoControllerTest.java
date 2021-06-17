package sia.tacocloudm;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sia.tacocloudm.domain.Taco;
import sia.tacocloudm.repository.TacoRepository;
import sia.tacocloudm.resources.DesignTacoResources;
import sia.tacocloudm.service.TacoService;

import java.util.*;

public class DesignTacoControllerTest {

    @Test
    public void shoulReturnRecentTacos() {
        Taco[] tacos = {
                testTaco(1L), testTaco(2L),
                testTaco(3L), testTaco(4L),
                testTaco(5L), testTaco(6L),
                testTaco(7L), testTaco(8L),
                testTaco(9L), testTaco(10L),
                testTaco(11L), testTaco(12L),
                testTaco(13L), testTaco(14L),
                testTaco(15L), testTaco(16L)};
        Flux<Taco> tacoFlux = Flux.just(tacos);

        var tacoService = Mockito.mock(TacoService.class);
        Mockito.when(tacoService.getRecentTacos()).thenReturn(tacoFlux);

        WebTestClient testClient = WebTestClient.bindToController(new DesignTacoResources(tacoService)).build();

        testClient.get().uri("/designtaco/recent")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$").isNotEmpty()
                .jsonPath("$[0].id").isEqualTo(tacos[0].getId().toString())
                .jsonPath("$[0].name").isEqualTo("Taco 1").jsonPath("$[1].id")
                .isEqualTo(tacos[1].getId().toString()).jsonPath("$[1].name")
                .isEqualTo("Taco 2").jsonPath("$[11].id")
                .isEqualTo(tacos[11].getId().toString())
                .jsonPath("$[11].name").isEqualTo("Taco 12").jsonPath("$[12]")
                .doesNotExist()
                .jsonPath("$[12]").doesNotExist();
    }

    @Test
    public void shouldSaveATaco() {
        var tacoService = Mockito.mock(TacoService.class);
        Mono<Taco> unsave = Mono.just(testTaco(null));
        Taco save = testTaco(null);
        Mono<Taco> saveTacoMono = Mono.just(save);

        Mockito.when(tacoService.save(Mockito.any())).thenReturn(saveTacoMono);

        var webClientTest = WebTestClient.bindToController(new DesignTacoResources(tacoService)).build();

        webClientTest.post()
                .uri("/designtaco")
                .contentType(MediaType.APPLICATION_JSON)
                .body(unsave, Taco.class)
                .exchange()
                .expectStatus().isCreated();
    }

    private Taco testTaco(Long number) {
        Taco taco = new Taco();
        taco.setId(number);
        taco.setName("Taco " + number);
        Set<String> ingredients = new HashSet<>();
        ingredients.add("INGA");
        ingredients.add("INGB");
        taco.setIngredientIds(ingredients);
        return taco;
    }
}
