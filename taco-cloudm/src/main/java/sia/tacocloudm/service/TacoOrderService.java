package sia.tacocloudm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import sia.tacocloudm.domain.Order;
import sia.tacocloudm.domain.TacoOrder;
import sia.tacocloudm.messaging.OrderMessagingService;
import sia.tacocloudm.repository.OrderRepository;
import sia.tacocloudm.repository.TacoOrderRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TacoOrderService {

    private final OrderRepository orderRepository;
    private final OrderMessagingService orderMessagingService;
    private final TacoOrderRepository tacoOrderRepository;

    @Transactional
    public Mono<Order> save(final Order order) {
        return Mono.just(order)
                .flatMap(value -> orderRepository.save(value))
                .map(entity -> {
                    tacoOrderRepository.saveAll(create(entity.getId(), order.getTacoIds()));
                    return entity;
                })
                .log()
                .map(orderCreated -> {
                    orderMessagingService.sendOrder(orderCreated);
                    return orderCreated;
                }).onErrorResume(i -> Mono.error(new RuntimeException("Fail create taco order " + i.getMessage()))).log();
    }

    private List<TacoOrder> create(final Long orderId, final Set<Long> idTacos) {
        return idTacos.stream().map(t -> new TacoOrder(orderId, t)).collect(Collectors.toList());
    }
}
