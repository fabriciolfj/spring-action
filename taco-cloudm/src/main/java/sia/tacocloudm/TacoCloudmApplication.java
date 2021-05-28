package sia.tacocloudm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import sia.tacocloudm.domain.Ingredient;
import sia.tacocloudm.repository.IngredientRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "sia.tacocloudm.repository")
public class TacoCloudmApplication implements CommandLineRunner {

    @Autowired
    private IngredientRepository repo;

    public static void main(String[] args) {
        SpringApplication.run(TacoCloudmApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repo.save(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));
        repo.save(new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP));
        repo.save(new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN));
        repo.save(new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN));
        repo.save(new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES));
        repo.save(new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES));
        repo.save(new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE));
        repo.save(new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE));
        repo.save(new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE));
        repo.save(new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE));
    }
}
