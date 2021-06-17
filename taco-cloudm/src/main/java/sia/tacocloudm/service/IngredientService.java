package sia.tacocloudm.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sia.tacocloudm.domain.Ingredient;
import sia.tacocloudm.repository.IngredientRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    public Flux<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }

    public Mono<Ingredient> findById(final String id) {
        return ingredientRepository.findById(id)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new RuntimeException("Ingredient not found: " + id))));
    }

    public Mono<Ingredient> update(final String id, final Ingredient ingredient) {
        return ingredientRepository.findById(id)
                .flatMap(entity -> {
                    BeanUtils.copyProperties(ingredient, entity, "id");
                    return ingredientRepository.save(entity);
                }).switchIfEmpty(Mono.defer(() -> Mono.error(new RuntimeException("Ingredient not found: " + id))));
    }

    public Mono<Void> delete(final String id) {
        return ingredientRepository.deleteById(id);
    }

    public Mono<Ingredient> save(final Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }
}
