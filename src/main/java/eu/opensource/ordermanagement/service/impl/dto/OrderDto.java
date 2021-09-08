package eu.opensource.ordermanagement.service.impl.dto;

import eu.opensource.ordermanagement.domain.LineItem;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class OrderDto {

    private final Long order_id;

    private final LocalDateTime orderDate;

    private final BigDecimal totalAmount;

    private final Long customer_id;

    private final String customerName;

    private List<LineItem> lineItems = new ArrayList<>();

    public OrderDto(Long order_id, LocalDateTime orderDate, BigDecimal totalAmount, Long customer_id, String customerName, List<LineItem> lineItems) {

        this.order_id = order_id;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.customer_id = customer_id;
        this.customerName = customerName;
        this.lineItems = lineItems;
    }
}
