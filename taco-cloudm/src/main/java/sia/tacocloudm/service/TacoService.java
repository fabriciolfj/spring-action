package sia.tacocloudm.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sia.tacocloudm.domain.Taco;
import sia.tacocloudm.repository.TacoRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TacoService {

    private final TacoRepository tacoRepository;

    public Optional<Taco> findById(final Long id) {
        return tacoRepository.findById(id);
    }

    public List<Taco> getRecentTacos() {
        var pageable = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        return tacoRepository.findAll(pageable).getContent();
    }

    public Taco save(final Taco taco) {
        return tacoRepository.save(taco);
    }

    public Taco update(final Taco taco, final Long id) {
        return tacoRepository.findById(id)
                .map(entity -> {
                    BeanUtils.copyProperties(taco, entity, "id");
                    return tacoRepository.save(entity);
                })
                .orElseThrow(() ->  new RuntimeException("Taco not found: " + id));
    }
}
