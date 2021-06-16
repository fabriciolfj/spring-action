package sia.tacocloudm.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import sia.tacocloudm.domain.Order;
import sia.tacocloudm.repository.OrderRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderAdminService {

    private final OrderRepository orderRepository;

    public void deleteAllOrders() {
        orderRepository.deleteAll();
    }

    public Mono<Order> getOrder(final Long id) {
        return orderRepository.findById(id)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new RuntimeException("Order not found: " + id))));
    }

    public void deleteOrder(final Long id) {
        try {
            orderRepository.deleteById(id);
        } catch (Exception e) {
            log.info("Order not found too delete: {}", id);
        }
    }
}
