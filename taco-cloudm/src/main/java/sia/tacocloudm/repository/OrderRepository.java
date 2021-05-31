package sia.tacocloudm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sia.tacocloudm.domain.TacoOrder;
import sia.tacocloudm.domain.User;

import java.util.List;

public interface OrderRepository extends JpaRepository<TacoOrder, Long> {

    List<TacoOrder> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
