package sia.tacocloudm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sia.tacocloudm.config.OrderProps;
import sia.tacocloudm.domain.Taco;
import sia.tacocloudm.domain.TacoOrder;
import sia.tacocloudm.domain.User;
import sia.tacocloudm.messaging.OrderMessagingService;
import sia.tacocloudm.repository.OrderRepository;
import sia.tacocloudm.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TacoOrderService {

    private final OrderRepository orderRepository;
    private final OrderProps orderProps;
    private final OrderMessagingService orderMessagingService;
    private final TacoService tacoService;
    private final UserRepository userRepository;

    public List<TacoOrder> getList(final User user) {
        var pageable = PageRequest.of(0, orderProps.getPageSize());
        return orderRepository.findByUserOrderByPlacedAtDesc(user, pageable);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void save(final TacoOrder order) {
        Optional.of(order)
                .map(entity -> {
                    order.setUser(userRepository.findByUsername("admin"));
                    order.setTacos(getTacos(order.getTacos()));
                    return entity;
                })
                .map(value -> orderRepository.save(value))
                .map(orderCreated -> {
                    orderMessagingService.sendOrder(orderCreated);
                    return orderCreated;
                }).orElseThrow(() -> new RuntimeException("Fail create taco order"));
    }

    public List<Taco> getTacos(final List<Taco> temp) {
        final List<Taco> tacos = new ArrayList<>();
        temp.forEach(i -> {
            tacos.add(tacoService.findById(i.getId())
                    .orElseThrow(() -> new RuntimeException("Order not found " + i)));
        });

        return tacos;
    }
}
