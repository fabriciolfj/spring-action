package sia.tacocloudm;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import sia.tacocloudm.domain.Ingredient;
import sia.tacocloudm.domain.User;
import sia.tacocloudm.repository.IngredientRepository;
import sia.tacocloudm.repository.UserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "sia.tacocloudm.repository")
public class TacoCloudmApplication implements CommandLineRunner {

    @Autowired
    private IngredientRepository repo;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

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

        userRepository.save(
                User.builder()
                        .city("Serrana")
                        .fullname("admin")
                        .password(passwordEncoder.encode("admin"))
                        .phoneNumber("16988485252")
                        .state("SP")
                        .street("Rua acre n122")
                        .username("admin")
                        .zip("14150000")
                        .build()
        );
    }
}
