package eu.opensource.ordermanagement.web.controller;

import eu.opensource.ordermanagement.domain.*;
import eu.opensource.ordermanagement.service.OrderService;
import eu.opensource.ordermanagement.service.impl.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping
@Controller
public class OrderController {

    private final Cart cart;

    private final OrderService orderService;

    @ModelAttribute
    public void addAttributes(Model uiModel) {

        uiModel.addAttribute("cart", cart);
    }

    @GetMapping("/customers/{orderId}")
    public String viewOrderById(@PathVariable Long orderId, Model uiModel) {

        OrderDto orderDto = orderService.getOrderById(orderId);

        uiModel.addAttribute("order", orderDto);

        return "orders/orderView";
    }

    @GetMapping("/customers/orders")
    public String viewAllOrders(Model uiModel) {

        List<OrderDto> orders = orderService.getAllOrders();

        uiModel.addAttribute("orders", orders);

        return "orders/ordersList";
    }

    @GetMapping("/{customerId}/orders")
    public String viewOrdersByCustomer(Authentication authentication, Model uiModel) {

        Customer customer = (Customer) authentication.getPrincipal();
        Long customerId = customer.getId();

        List<OrderDto> orders = orderService.getOrderByCustomer(customerId);

        uiModel.addAttribute("orders", orders);

        return "orders/ordersList";
    }

    @GetMapping("/customers/checkout")
    public String viewOrder(Authentication authentication, Model uiModel) {

        Customer customer = (Customer) authentication.getPrincipal();

        uiModel.addAttribute("customer", customer);

        return "orders/orderCheckout";
    }

    @GetMapping
    public String saveOrder(Authentication authentication){

        orderService.saveOrder();
        cart.clearCart();

        return "redirect:/";
    }
}
