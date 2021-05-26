package sia.tacocloudm;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

   @Test
    public void testHomePage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(content().string(Matchers.containsString("Welcome to...")));
    }

    @Test
    public void test() {
        var nome = "Fabricio";
        Consumer<String> teste3 = (result) -> {
            var teste1 = result.toUpperCase();
            System.out.println(teste1);
        };

        teste3.accept("Fabricio2");

        Supplier<String> teste2 = () -> {
            var teste1 = "teste".toUpperCase();
            System.out.println(teste1);
            return teste1;
        };

        up(nome, () -> {
            var teste1 = "teste".toUpperCase();
            System.out.println(teste1);
            return teste1;
        });
    }

    private void up(String nome, Supplier<String> o) {
        System.out.println(nome + " " + o.get());
    }

    private String up(final String name) {
        return name.toUpperCase();
    }
}
