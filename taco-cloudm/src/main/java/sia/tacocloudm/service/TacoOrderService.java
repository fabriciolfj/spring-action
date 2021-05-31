package sia.tacocloudm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import sia.tacocloudm.config.OrderProps;
import sia.tacocloudm.domain.TacoOrder;
import sia.tacocloudm.domain.User;
import sia.tacocloudm.repository.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TacoOrderService {

    private final OrderRepository orderRepository;
    private final OrderProps orderProps;

    public List<TacoOrder> getList(final User user) {
        var pageable = PageRequest.of(0, orderProps.getPageSize());
        return orderRepository.findByUserOrderByPlacedAtDesc(user, pageable);
    }

    public void save(final TacoOrder order) {
        orderRepository.save(order);
    }
}
