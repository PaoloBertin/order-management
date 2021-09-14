package eu.opensource.ordermanagement.web.util;

import eu.opensource.ordermanagement.domain.Cart;
import eu.opensource.ordermanagement.domain.Product;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


@Getter
public class CartSingleton {

    private Map<Product, Integer> cartItems = new HashMap<>();

    private BigDecimal totalAmount = BigDecimal.ZERO;

    public CartSingleton(Cart cart) {

        this.cartItems = cart.getCartItems();
        this.totalAmount = cart.getTotalAmount();
    }
}
