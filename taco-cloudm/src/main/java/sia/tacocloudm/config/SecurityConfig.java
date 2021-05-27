package sia.tacocloudm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import sia.tacocloudm.repository.UserRepository;

@Configuration
public class SecurityConfig  {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> {
            var user = userRepository.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("User :" + username + " not found");
            }

            return user;
        };
    }

    /*
    * A ordem possui procedência, ou seja, a primeira e sobre sai sobre a segunda.
    * usado para aplicacao mvn, para rest usa-se WebSecurityConfigurerAdapter
    * */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/design", "/orders", "/orders/current")
                .hasRole("USER")
                .antMatchers("/", "/**").permitAll()
                .and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/design", true)
                .and()
                .oauth2Login().loginPage("/login")
                .and().build();
    }

    /*@Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        var userList = new ArrayList<UserDetails>();
        userList.add(new User("buzz", encoder.encode("password"),
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
        userList.add(new User("woody", encoder.encode("password"),
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
        return new InMemoryUserDetailsManager(userList);
    }*/
}
