package eu.opensource.ordermanagement.web.controller;

import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
// @SpringBootTest
class OrderAdminControllerTest {

    @Autowired
    private MockMvc mvc;

    private final String url = "/admin/orders";

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
//    @Test
    void viewAllOrdersSuccessTest() throws Exception {

        mvc.perform(get(url).with(httpBasic("admin@email.com", "admin")))
           .andExpect(status().isOk())
           .andExpect(model().attribute("orders", IsCollectionWithSize.hasSize(10)))
           .andExpect(view().name("orders/ordersList"))
        ;
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
//    @Test
    void viewAllOrdersFailureTest() throws Exception {

        mvc.perform(get(url).with(httpBasic("rossi@email.com", "rossi")))
           .andExpect(status().isForbidden())
        ;
    }

}