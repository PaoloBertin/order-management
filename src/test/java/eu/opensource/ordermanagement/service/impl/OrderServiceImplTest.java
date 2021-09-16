package eu.opensource.ordermanagement.service.impl;

import eu.opensource.ordermanagement.service.OrderService;
import eu.opensource.ordermanagement.service.impl.dto.OrderDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Test
    void getOrderById() {

        long orderId = 1L;
        OrderDto orderDto = orderService.getOrderById(orderId);
        assertThat(orderDto.getTotalAmount()).isEqualByComparingTo(BigDecimal.valueOf(17.00));
    }

    @Test
    void getAllOrders() {

    }

    @Test
    void getOrderByCustomer() {

    }

    @Test
    void saveOrder() {

    }
}