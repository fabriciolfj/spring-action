package sia.tacocloudm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sia.tacocloudm.domain.Ingredient;


public interface IngredientRepository extends JpaRepository<Ingredient, String> {

}
