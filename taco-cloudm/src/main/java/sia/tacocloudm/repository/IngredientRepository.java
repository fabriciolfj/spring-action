package sia.tacocloudm.repository;

import org.springframework.data.repository.CrudRepository;
import sia.tacocloudm.domain.Ingredient;


public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
