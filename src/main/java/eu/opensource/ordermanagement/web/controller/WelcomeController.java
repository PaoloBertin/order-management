package eu.opensource.ordermanagement.web.controller;

import eu.opensource.ordermanagement.domain.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/")
@Controller
public class WelcomeController {

    private final Cart cart;

    @GetMapping
    public String homePage(Model uiModel) {

        uiModel.addAttribute("itemsInCart", cart.getNumberOfItems());

        return "welcome";
    }

    @GetMapping("/delivery")
    public String deliveryPage(Model uiModel) {

        uiModel.addAttribute("itemsInCart", cart.getNumberOfItems());

        return "delivery";
    }
}
