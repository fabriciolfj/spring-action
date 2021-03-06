package sia.tacocloudm.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import sia.tacocloudm.domain.TacoOrder;
import sia.tacocloudm.domain.User;
import sia.tacocloudm.repository.OrderRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderAdminService {

    private final OrderRepository orderRepository;

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAllOrders() {
        orderRepository.deleteAll();
    }

    @PostAuthorize("hasRole('ADMIN') || returnObject.user.username == authentication.name")
    public TacoOrder getOrder(final Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found " + id));
    }

    public void deleteOrder(final Long id) {
        try {
            orderRepository.deleteById(id);
        } catch (Exception e) {
            log.info("Order not found too delete: {}", id);
        }
    }
}
