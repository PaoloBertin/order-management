package eu.opensource.ordermanagement.service;

import eu.opensource.ordermanagement.domain.Order;
import eu.opensource.ordermanagement.service.impl.dto.OrderDto;

import java.util.List;

public interface OrderService {

    List<OrderDto> getAllOrders();

    List<OrderDto> getOrderByCustomer(Long customerId);

    OrderDto getOrderById(Long id);

    Order saveOrder();
}
