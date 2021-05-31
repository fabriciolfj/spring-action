package sia.tacocloudm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sia.tacocloudm.service.OrderAdminService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final OrderAdminService orderAdminService;

    @PostMapping("/deleteOrders")
    public String deleteAllOrders() {
        orderAdminService.deleteAllOrders();
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("id") final Long id) {
        orderAdminService.deleteOrder(id);
    }
}
