package sia.tacocloudm.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sia.tacocloudm.domain.Taco;
import sia.tacocloudm.service.TacoService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/designtaco")
@CrossOrigin(origins = "*")
public class DesignTacoResources {

    private final TacoService tacoService;

    @GetMapping("/recent")
    public Flux<Taco> recentTacos() {
        return tacoService.getRecentTacos();
    }

    @GetMapping("/{id}")
    public Mono<Taco> tacoById(@PathVariable("id") final Long id) {
        return tacoService.findById(id);
    }

    @PostMapping
    public Mono<Taco> postTaco(@RequestBody final Taco taco) {
        return tacoService.save(taco);
    }

    @PutMapping("/{id}")
    public Mono<Taco> putTaco(@PathVariable("id") final Long id, @RequestBody final Taco taco) {
        return tacoService.update(taco, id);
    }
}
