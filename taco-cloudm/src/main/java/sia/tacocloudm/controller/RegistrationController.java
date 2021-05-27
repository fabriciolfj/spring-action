package sia.tacocloudm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sia.tacocloudm.dto.RegistrationForm;
import sia.tacocloudm.repository.UserRepository;

@Controller
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public String registerForm() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(final RegistrationForm form) {
        userRepository.save(form.toUser(passwordEncoder));
        return "redirect:/login";
    }
}
