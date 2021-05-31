package sia.tacocloudm.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import sia.tacocloudm.domain.Ingredient;
import sia.tacocloudm.domain.Taco;
import sia.tacocloudm.repository.IngredientRepository;
import sia.tacocloudm.repository.TacoRepository;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private TacoRepository tacoRepository;

    @GetMapping
    public String showDesignForm(final Model model) {
        model.addAttribute("taco", new Taco());
        return "design";
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors){
        if(errors.hasErrors()) {
            return "design";
        }

        log.info("Processing taco: {}", taco);
        tacoRepository.save(taco);
        return "redirect:/orders/current";
    }

    @ModelAttribute
    public void addIngredientsToModel(final Model model) {
        List<Ingredient> ingredients = (List<Ingredient>) ingredientRepository.findAll();
        var types = Ingredient.Type.values();
        for (Ingredient.Type type: types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }

    private Iterable<Ingredient> filterByType(final List<Ingredient> ingredients, final Ingredient.Type type){
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
