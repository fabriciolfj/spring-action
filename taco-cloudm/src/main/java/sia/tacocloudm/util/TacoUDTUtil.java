package sia.tacocloudm.util;

import lombok.experimental.UtilityClass;
import sia.tacocloudm.domain.Ingredient;
import sia.tacocloudm.domain.IngredientUDT;
import sia.tacocloudm.domain.Taco;
import sia.tacocloudm.domain.TacosUDT;

@UtilityClass
public class TacoUDTUtil {

    public static IngredientUDT convert(final Ingredient ingredient) {
        return IngredientUDT
                .builder()
                .name(ingredient.getName())
                .type(ingredient.getType())
                .build();
    }

    public static TacosUDT convert(final Taco taco) {
        return TacosUDT.builder()
                .name(taco.getName())
                .ingredients(taco.getIngredients())
                .build();
    }
}
