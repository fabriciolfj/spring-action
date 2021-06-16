package sia.tacocloudm.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import sia.tacocloudm.domain.TacoOrder;

public interface TacoOrderRepository extends ReactiveCrudRepository<TacoOrder, Long> {
}
