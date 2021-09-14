package eu.opensource.ordermanagement.web.controller;

import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class OrderControllerTest {

    @Autowired
    private MockMvc mvc;

    private String url = "/orders";

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void viewOrderByIdTest() throws Exception {

        mvc.perform(get("/orders/all/{orderId}", 1L).with(user("rossi@emal.com").password("rossi")
                                                                                .roles("USER")))
           .andExpect(status().isOk())
           .andExpect(model().attribute("orders", IsCollectionWithSize.hasSize(10)))
           .andExpect(view().name("orders/ordersList"))
        ;
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void viewAllOrdersSuccessTest() throws Exception {

        mvc.perform(get("/orders/all").with(user("admin@emal.com").password("admin")
                                                                  .roles("ADMIN")))
           .andExpect(status().isOk())
           .andExpect(model().attribute("orders", IsCollectionWithSize.hasSize(10)))
           .andExpect(view().name("orders/ordersList"))
        ;
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void viewAllOrdersFailureTest() throws Exception {

        mvc.perform(get("/orders/all").with(user("user@emal.com").password("user")
                                                                 .roles("USER")))
           .andExpect(status().isForbidden())
        ;
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    @WithUserDetails(value = "admin@email.com")
    void viewOrdersByCustomerTest() throws Exception {

        mvc.perform(get("/orders/{customerId}", 1L))
           .andExpect(status().isOk())
           .andExpect(model().attribute("orders", IsCollectionWithSize.hasSize(4)))
           .andExpect(view().name("orders/ordersList"))
        ;
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void viewOrder() throws Exception {

        mvc.perform(get(url + "/checkout"))
           .andExpect(status().isOk());
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void saveOrder() throws Exception {

    }
}