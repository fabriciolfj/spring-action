package sia.tacocloudm.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import sia.tacocloudm.domain.Order;

public interface OrderRepository extends ReactiveCrudRepository<Order, Long> {
}
