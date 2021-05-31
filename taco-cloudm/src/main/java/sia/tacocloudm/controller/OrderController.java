package sia.tacocloudm.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import sia.tacocloudm.domain.TacoOrder;
import sia.tacocloudm.domain.User;
import sia.tacocloudm.service.TacoOrderService;

import javax.validation.Valid;
import java.util.Date;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final TacoOrderService tacoOrderService;

    @GetMapping("/current")
    public String orderForm(Model model) {
        model.addAttribute("tacoOrder", new TacoOrder());
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder tacoOrder, Errors errors, SessionStatus sessionStatus, @AuthenticationPrincipal User user) {
        if(errors.hasErrors()) {
            return "orderForm";
        }

        log.info("Order submitted: {}", tacoOrder);
        tacoOrder.setPlacedAt(new Date());
        tacoOrder.setUser(user);
        tacoOrderService.save(tacoOrder);
        sessionStatus.setComplete();
        return "redirect:/";
    }

    @GetMapping
    public String ordersForUser(final @AuthenticationPrincipal User user, final Model model) {
        model.addAttribute("orders", tacoOrderService.getList(user));
        return "orderList";
    }

}
