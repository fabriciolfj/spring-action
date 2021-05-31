package sia.tacocloudm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sia.tacocloudm.domain.Taco;

public interface TacoRepository extends JpaRepository<Taco, Long> {
}
