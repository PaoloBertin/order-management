package eu.opensource.ordermanagement.service.impl;

import eu.opensource.ordermanagement.domain.*;
import eu.opensource.ordermanagement.repository.OrderRepository;
import eu.opensource.ordermanagement.service.OrderService;
import eu.opensource.ordermanagement.service.impl.dto.OrderDto;
import eu.opensource.ordermanagement.service.impl.dto.OrderDtoBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    private final Cart cart;

    private final OrderRepository orderRepository;

    @Override
    public OrderDto getOrderById(Long orderId) {

        Optional<Order> order = orderRepository.findById(orderId);
        OrderDto orderDto = OrderDtoBuilder.newOrderDto(order.get()).build();
        return orderDto;
    }

    @Override
    public List<OrderDto> getAllOrders() {

        List<Order> orders = orderRepository.findAll();
        List<OrderDto> orderDtos = new ArrayList<>();
        for (Order order : orders) {
            OrderDto orderDto = OrderDtoBuilder.newOrderDto(order).build();
            orderDtos.add(orderDto);
        }

        return orderDtos;
    }

    @Override
    public List<OrderDto> getOrderByCustomer(Long customerId) {

        List<Order> orders = orderRepository.findByCustomerId(customerId);
        List<OrderDto> orderDtos = new ArrayList<>();
        for (Order order : orders) {
            OrderDto orderDto = OrderDtoBuilder.newOrderDto(order)
                    .build();
            orderDtos.add(orderDto);
        }

        return orderDtos;
    }

    @Override
    public Order saveOrder() {

        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        Customer customer = (Customer) authentication.getPrincipal();

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());
        order.setTotalAmount(cart.getTotalAmount());
        List<LineItem> lineItems = new ArrayList<>();
        for (Map.Entry<Product, Integer> item : cart.getCartItems().entrySet()) {
            LineItem lineItem = new LineItem();
            lineItem.setProduct(item.getKey());
            lineItem.setQuantity(item.getValue());
            lineItems.add(lineItem);
        }
        order.setLineItems(lineItems);

        return orderRepository.save(order);
    }
}
