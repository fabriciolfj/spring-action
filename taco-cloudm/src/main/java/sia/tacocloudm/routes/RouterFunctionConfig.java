package sia.tacocloudm.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import sia.tacocloudm.domain.Taco;
import sia.tacocloudm.service.TacoService;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterFunctionConfig {

    @Autowired
    private TacoService tacoService;

    @Bean
    public RouterFunction<ServerResponse> routes() {
        return route(GET("/v2/designtaco").and(accept(APPLICATION_JSON)), this::recents)
                .andRoute(POST("/v2/designtaco").and(accept(APPLICATION_JSON)), this::postTaco);
    }

    private Mono<ServerResponse> recents(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .body(tacoService.getRecentTacos(), Taco.class);
    }

    private Mono<ServerResponse> postTaco(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Taco.class)
                .flatMap(t -> tacoService.save(t))
                .flatMap(entity -> ServerResponse.created(
                        URI.create("http://localhost:8080/v2/designtaco/" + entity.getId()))
                        .bodyValue(entity)
                );
    }
}
