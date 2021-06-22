package sia.tacocloudm.rsocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
@Slf4j
public class GreetingController {

    /*
    * modelo request-response
    * */
    @MessageMapping("greeting")
    public Mono<String> handleGreeting(final Mono<String> greetingMono) {
        return greetingMono.doOnNext(greeting -> log.info("Received a greeting: {}", greeting))
                .map(g -> "Hello back to you!");
    }

    @MessageMapping("greeting/{name}")
    public Mono<String> handleGreeting(@DestinationVariable("name") final String name, final Mono<String> greetingMono) {
        return greetingMono.doOnNext(greeting -> log.info("Received a greeting from {} : {}", name, greeting))
                .map(g -> "Hello to you, too," + name);
    }
}
