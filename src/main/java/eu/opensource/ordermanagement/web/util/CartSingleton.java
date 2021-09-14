package eu.opensource.ordermanagement.web.util;

import eu.opensource.ordermanagement.domain.Cart;
import eu.opensource.ordermanagement.domain.Product;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Map;


@Getter
public class CartSingleton {

    private final Map<Product, Integer> cartItems;

    private final BigDecimal totalAmount;

    public CartSingleton(Cart cart) {

        this.cartItems = cart.getCartItems();
        this.totalAmount = cart.getTotalAmount();
    }
}
