package sia.tacocloudm.rsocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import sia.tacocloudm.domain.GratuityIn;
import sia.tacocloudm.domain.GratuityOut;

import java.math.BigDecimal;

@Controller
@Slf4j
public class GratuityController {

    /*
    * modelo canal flux < - > flux
    * */
    @MessageMapping("gratuity")
    public Flux<GratuityOut> calculate(Flux<GratuityIn> gratuityInFlux) {
        return gratuityInFlux.doOnNext(in -> log.info("Calculating gratuity: " + in))
                .map(in -> {
                    double percentAsDecimal = in.getPercent() / 100.0;
                    var gratuity = in.getBillTotal().multiply(BigDecimal.valueOf(percentAsDecimal));
                    return new GratuityOut(in.getBillTotal(), in.getPercent(), gratuity);
                });
    }
}
