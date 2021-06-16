package sia.tacocloudm.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import sia.tacocloudm.domain.TacoIngredient;

public interface TacoIngredientRepository extends ReactiveCrudRepository<TacoIngredient, Long> {
}
