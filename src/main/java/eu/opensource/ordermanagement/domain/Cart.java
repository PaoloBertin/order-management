package eu.opensource.ordermanagement.domain;

import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Map<Product, Integer> cartItems = new HashMap<>();

    private BigDecimal totalAmount = BigDecimal.ZERO;

    public Map<Product, Integer> getCartItems() {

        return cartItems;
    }

    public int getNumberOfItems() {

        int numberOfElements = 0;
        for (Map.Entry<Product, Integer> entry : cartItems.entrySet()) {
            numberOfElements += entry.getValue();
        }
        return numberOfElements;
    }

    public BigDecimal getTotalAmount() {

        return totalAmount;
    }

    public void addProductToCart(Product product) {

        if (cartItems.containsKey(product)) {
            int quantity = cartItems.get(product);
            quantity++;
            cartItems.put(product, quantity);
        } else {
            cartItems.put(product, 1);
        }
        calculateTotal();
    }

    public void removeProductFromCart(Product product) {

        cartItems.remove(product);
    }

    public void clearCart() {

        cartItems.clear();
    }

    private void calculateTotal() {

        totalAmount = BigDecimal.ZERO;
        for (Map.Entry<Product, Integer> item : cartItems.entrySet()) {
            BigDecimal price = item.getKey()
                                   .getPrice();
            int quantity = item.getValue();
            BigDecimal subtotal = price.multiply(BigDecimal.valueOf(quantity));
            totalAmount = totalAmount.add(subtotal);
        }
    }
}
