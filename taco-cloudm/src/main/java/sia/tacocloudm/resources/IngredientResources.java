package sia.tacocloudm.resources;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sia.tacocloudm.domain.Ingredient;
import sia.tacocloudm.service.IngredientService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ingredients")
@CrossOrigin(origins = "*")
public class IngredientResources {

    private final IngredientService ingredientService;

    @GetMapping
    public List<Ingredient> findAll() {
        return ingredientService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> findById(@PathVariable("id") final String id) {
        return  ingredientService.findById(id)
                .map(entity -> ResponseEntity.ok(entity))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Ingredient update(@PathVariable("id") final String id, @RequestBody final Ingredient ingredient) {
        return ingredientService.update(id, ingredient);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") final String id) {
        ingredientService.delete(id);
    }

    @PostMapping
    public Ingredient create(@RequestBody final Ingredient ingredient) {
        return ingredientService.save(ingredient);
    }
}
