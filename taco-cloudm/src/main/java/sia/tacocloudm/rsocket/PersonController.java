package sia.tacocloudm.rsocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import sia.tacocloudm.domain.PersonIn;
import sia.tacocloudm.domain.PersonOut;

import java.util.UUID;

@Controller
@Slf4j
public class PersonController {

    @MessageMapping("person")
    public Flux<PersonOut> addCode(Flux<PersonIn> fluxPerson) {
        return fluxPerson.doOnNext(p -> log.info("Receive : {}", p.toString()))
                .map(p -> {
                    var code = UUID.randomUUID().toString();
                    return new PersonOut(p.getName(), code);
                });
    }
}
