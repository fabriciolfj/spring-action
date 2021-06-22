package sia.tacocloudm.rsocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;
import sia.tacocloudm.domain.Alert;

@Controller
@Slf4j
public class AlertController {

    /*
    * modelo fire-and-forget, onde envio um mono e não tenho resposta
    *
    * */
    @MessageMapping("alert")
    public Mono<Void> setAlert(final Mono<Alert> alertMono) {
        return alertMono.doOnNext(alert -> {
            log.info(alert.getLevel() + " alert" + " ordered by " + alert.getOrderedBy() + " at " + alert.getOrderedAt());
        }).thenEmpty(Mono.empty());
    }
}
