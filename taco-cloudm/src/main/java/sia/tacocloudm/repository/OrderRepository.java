package sia.tacocloudm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sia.tacocloudm.domain.TacoOrder;

public interface OrderRepository extends JpaRepository<TacoOrder, Long> {
}
