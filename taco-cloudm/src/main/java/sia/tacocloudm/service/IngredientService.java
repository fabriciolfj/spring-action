package sia.tacocloudm.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import sia.tacocloudm.domain.Ingredient;
import sia.tacocloudm.repository.IngredientRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }

    public Optional<Ingredient> findById(final String id) {
        return ingredientRepository.findById(id);
    }

    public Ingredient update(final String id, final Ingredient ingredient) {
        return ingredientRepository.findById(id)
                .map(entity -> {
                    BeanUtils.copyProperties(ingredient, entity, "id");
                    return ingredientRepository.save(entity);
                }).orElseThrow(() -> new RuntimeException("Ingredient not found " + id));
    }

    public void delete(final String id) {
        try {
            ingredientRepository.deleteById(id);
        } catch (Exception e) {
            log.info("Fail delete ingredient, ID: {}", id);
        }
    }
}
