package sia.tacocloudm.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import sia.tacocloudm.domain.Taco;

public interface TacoRepository extends ReactiveCrudRepository<Taco, Long> {
}
