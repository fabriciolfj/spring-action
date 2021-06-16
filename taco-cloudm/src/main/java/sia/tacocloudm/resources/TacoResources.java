package sia.tacocloudm.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import sia.tacocloudm.domain.Order;
import sia.tacocloudm.service.TacoOrderService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ordersapi")
public class TacoResources {

    private final TacoOrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Order> create(@RequestBody final Order order) {
        return orderService.save(order);
    }
}
