package sia.tacocloudm.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import sia.tacocloudm.domain.Ingredient;


public interface IngredientRepository extends ReactiveCrudRepository<Ingredient, String> {

}
