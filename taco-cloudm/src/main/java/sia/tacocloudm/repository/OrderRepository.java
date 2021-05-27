package sia.tacocloudm.repository;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloudm.domain.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
}
