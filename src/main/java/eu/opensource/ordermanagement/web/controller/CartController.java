package eu.opensource.ordermanagement.web.controller;

import eu.opensource.ordermanagement.domain.Cart;
import eu.opensource.ordermanagement.domain.Product;
import eu.opensource.ordermanagement.service.CatalogService;
import eu.opensource.ordermanagement.web.util.CartSingleton;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@RequestMapping("/cart")
@Controller
public class CartController {

    private final Cart cart;

    private final CatalogService catalogService;

    @GetMapping("/add")
    public String addProductToCart(@RequestParam String productCode, Model uiModel) {

        Product product = catalogService.getProductByCode(productCode);
        cart.addProductToCart(product);
        uiModel.addAttribute("itemsInCart", cart.getNumberOfItems());

        return "redirect:/catalog/1";
    }

    @GetMapping("/show")
    public String showCart(Model uiModel) {

        CartSingleton cartSingleton = new CartSingleton(cart);
        uiModel.addAttribute("cart", cartSingleton);
        uiModel.addAttribute("itemsInCart", cart.getNumberOfItems());

        return "cart/showCart";
    }

    @GetMapping("/clear")
    public String clearCart(Model uiModel) {

        cart.clearCart();
        uiModel.addAttribute("itemsInCart", cart.getNumberOfItems());

        return "welcome";
    }
}
