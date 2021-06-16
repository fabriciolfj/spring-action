package sia.tacocloudm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sia.tacocloudm.domain.Taco;
import sia.tacocloudm.domain.TacoIngredient;
import sia.tacocloudm.repository.TacoIngredientRepository;
import sia.tacocloudm.repository.TacoRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class TacoService {

    private final TacoRepository tacoRepository;
    private final TacoIngredientRepository tacoIngredientRepository;

    public Mono<Taco> findById(final Long id) {
        return tacoRepository.findById(id)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new RuntimeException("Taco not found: " + id))));
    }

    public Flux<Taco> getRecentTacos() {
        return tacoRepository.findAll();
    }

    public Mono<Taco> save(final Taco taco) {
        return tacoRepository.save(taco)
                .map(t -> {
                    tacoIngredientRepository.saveAll(createIngredient(t, taco.getIngredientIds()));
                    return t;
                });
    }

    public Mono<Taco> update(final Taco taco, final Long id) {
        return tacoRepository.findById(id)
                .flatMap(entity -> {
                    BeanUtils.copyProperties(taco, entity, "id");
                    return tacoRepository.save(entity);
                })
                .switchIfEmpty(Mono.defer(() -> Mono.error(new RuntimeException("Taco not found: " + id))));
    }

    private List<TacoIngredient> createIngredient(final Taco taco, final Set<String> ingredients) {
        return ingredients.stream().map(i -> new TacoIngredient(taco.getId(), i))
        .collect(Collectors.toList());
    }
}
