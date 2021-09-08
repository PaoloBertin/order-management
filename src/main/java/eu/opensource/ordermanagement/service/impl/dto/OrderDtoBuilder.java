package eu.opensource.ordermanagement.service.impl.dto;

import eu.opensource.ordermanagement.domain.Order;

public class OrderDtoBuilder {

    private Order order;

    private OrderDtoBuilder(Order order) {

        this.order = order;
    }

    public static OrderDtoBuilder newOrderDto(Order order) {

        return new OrderDtoBuilder(order);
    }

    public OrderDto build() {

        return new OrderDto(order.getId(),
                            order.getOrderDate(),
                            order.getTotalAmount(),
                            order.getCustomer().getId(),
                            order.getCustomer().getFirstname() + " " + order.getCustomer().getLastname(),
                            order.getLineItems()
        );
    }
}
