package sia.tacocloudm.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import sia.tacocloudm.domain.Ingredient;
import sia.tacocloudm.domain.IngredientUDT;
import sia.tacocloudm.repository.IngredientRepository;
import sia.tacocloudm.util.TacoUDTUtil;

@Component
public class IngredientByIdConverter implements Converter<String, IngredientUDT> {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Override
    public IngredientUDT convert(String id) {
        return ingredientRepository.findById(id)
                .map(TacoUDTUtil::convert).orElse(null);
    }
}
