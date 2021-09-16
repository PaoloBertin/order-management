package eu.opensource.ordermanagement.web.controller;

import eu.opensource.ordermanagement.domain.Cart;
import eu.opensource.ordermanagement.domain.Customer;
import eu.opensource.ordermanagement.domain.Order;
import eu.opensource.ordermanagement.service.OrderService;
import eu.opensource.ordermanagement.service.impl.dto.OrderDto;
import eu.opensource.ordermanagement.web.util.CartSingleton;
import eu.opensource.ordermanagement.web.util.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor
@RequestMapping("/orders")
@Controller
public class OrderController {

    private final MessageSource messageSource;

    private final Cart cart;

    private final OrderService orderService;

    @GetMapping("/{orderId}/customers")
    public String viewOrderById(@PathVariable Long orderId, Model uiModel) {

        OrderDto orderDto = orderService.getOrderById(orderId);

        uiModel.addAttribute("order", orderDto);

        return "orders/orderView";
    }

    @GetMapping("/{customerId}")
    public String viewOrdersByCustomer(Authentication authentication, Model uiModel) {

        Customer customer = (Customer) authentication.getPrincipal();
        Long customerId = customer.getId();

        List<OrderDto> orders = orderService.getOrderByCustomer(customerId);

        uiModel.addAttribute("orders", orders);
        uiModel.addAttribute("itemsInCart", cart.getNumberOfItems());

        return "orders/ordersList";
    }

    @GetMapping("/checkout")
    public String viewOrder(Authentication authentication, Model uiModel) {

        Customer customer = (Customer) authentication.getPrincipal();

        uiModel.addAttribute("customer", customer);
        uiModel.addAttribute("cart", new CartSingleton(cart));
        uiModel.addAttribute("itemsInCart", cart.getNumberOfItems());

        return "orders/orderCheckout";
    }

    @PostMapping("/checkout")
    public String saveOrder(Authentication authentication, Model uiModel, RedirectAttributes redirectAttributes, Locale locale) {

        Message message = null;

        Order order = orderService.saveOrder();

        if (order.getId() > 0) {
            message = new Message("success", messageSource.getMessage("message.order_save_success", new Object[]{}, locale));
        } else {
            message = new Message("error", messageSource.getMessage("message.order_save_fail", new Object[]{}, locale));
        }
        redirectAttributes.addFlashAttribute("message", message);

        cart.clearCart();

        return "redirect:/";
    }
}
