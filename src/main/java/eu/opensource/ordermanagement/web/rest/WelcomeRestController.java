package eu.opensource.ordermanagement.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eu.opensource.ordermanagement.domain.Cart;

@RequestMapping("/")
@RestController
public class WelcomeRestController {

    final Logger log = LoggerFactory.getLogger(WelcomeRestController.class);

    private final Cart cart;

    public WelcomeRestController(Cart cart) {
        this.cart = cart;
    }

    @GetMapping
    public ResponseEntity<Integer> homePage() {

        Integer itemsInCart = cart.getNumberOfItems();
        log.debug("numero elementi nel carrello:" + itemsInCart);

        return ResponseEntity.ok(itemsInCart);
    }

    @GetMapping("/delivery")
    public ResponseEntity<Integer> deliveryPage() {

        Integer itemsInCart = cart.getNumberOfItems();
        log.debug("number of items in the cart" + itemsInCart);

        return ResponseEntity.ok(itemsInCart);
    }
}
