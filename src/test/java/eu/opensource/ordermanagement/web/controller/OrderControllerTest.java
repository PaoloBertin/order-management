package eu.opensource.ordermanagement.web.controller;

import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class OrderControllerTest {

    @Autowired
    private MockMvc mvc;

    private final String url = "/orders";

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void viewOrderByIdTest() throws Exception {

        mvc.perform(get(url + "/{orderId}", 1L).with(httpBasic("rossi@email.com", "rossi")))
           .andExpect(status().isOk())
           .andExpect(model().attribute("order", hasProperty("customerName", equalTo("Mario Rossi"))))
           .andExpect(model().attribute("order", hasProperty("lineItems", IsCollectionWithSize.hasSize(2))))
           .andExpect(view().name("orders/orderView"))
        ;
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void viewOrdersByCustomerTest() throws Exception {

        mvc.perform(get(url + "/{customerId}", 3).with(httpBasic("rossi@email.com", "rossi")))
           .andExpect(status().isOk())
           .andExpect(model().attribute("orders", IsCollectionWithSize.hasSize(4)))
           .andExpect(view().name("orders/ordersList"))
        ;
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void viewOrderTest() throws Exception {

        mvc.perform(get(url + "/checkout").with(httpBasic("rossi@email.com", "rossi")))
           .andExpect(model().attribute("customer", hasProperty("username", equalTo("rossi@email.com"))))
           .andExpect(status().isOk());
    }

    @Disabled
    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void saveOrderTest() throws Exception {

    }
}