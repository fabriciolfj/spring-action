package sia.tacocloudm.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public CollectionModel<EntityModel<Taco>> recentTacos() {
        var tacos = tacoService.getRecentTacos();

        var recentResources = CollectionModel.wrap(tacos);
        recentResources.add(Link.of("http://localhost:8080/designtaco/recent", "recents"));
        return recentResources;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") final Long id) {
        return tacoService.findById(id)
                .map(t -> ResponseEntity.ok(t))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Taco postTaco(@RequestBody final Taco taco) {
        return tacoService.save(taco);
    }

    @PutMapping("/{id}")
    public Taco putTaco(@PathVariable("id") final Long id, @RequestBody final Taco taco) {
        return tacoService.update(taco, id);
    }
}
