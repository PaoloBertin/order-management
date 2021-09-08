package eu.opensource.ordermanagement.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
class CartControllerTest {

    @Autowired
    private MockMvc mvc;

    private final String url = "/cart";

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void addProductToCartTest() throws Exception {

        mvc.perform(get(url + "/add").param("productCode", "P0001")
                                     .with(user("user@email.com").password("user")
                                                                 .roles("USER", "ADMIN")))
           .andExpect(status().isOk());
    }
}