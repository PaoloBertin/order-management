package eu.opensource.ordermanagement.web.controller;

import eu.opensource.ordermanagement.domain.Cart;
import eu.opensource.ordermanagement.domain.Product;
import eu.opensource.ordermanagement.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@RequestMapping("/cart")
@Controller
public class CartController {

    private final Cart cart;

    private final CatalogService catalogService;

    @ModelAttribute
    public void addAttributes(Model uiModel) {

        uiModel.addAttribute("cart", cart);
    }

    @GetMapping("/add")
    public String addProductToCart(@RequestParam String productCode) {

        Product product = catalogService.getProductByCode(productCode);
        cart.addProductToCart(product);

        return "redirect:/catalog/1";
    }

    @GetMapping("/show")
    public String showCart() {

        return "cart/showCart";
    }

    @GetMapping("/clear")
    public String clearCart() {

        cart.clearCart();

        return "welcome";
    }
}
