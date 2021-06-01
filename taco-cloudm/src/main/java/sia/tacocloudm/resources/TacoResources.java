package sia.tacocloudm.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sia.tacocloudm.domain.TacoOrder;
import sia.tacocloudm.service.TacoOrderService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ordersapi")
public class TacoResources {

    private final TacoOrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody final TacoOrder order) {
        orderService.save(order);
    }
}
