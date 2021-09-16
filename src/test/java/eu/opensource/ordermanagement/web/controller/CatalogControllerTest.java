package eu.opensource.ordermanagement.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class CatalogControllerTest {

    @Autowired
    private MockMvc mvc;

    private String url = "/catalog";

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void viewProductByCategoryTest() throws Exception {

        mvc.perform(get(url + "/{categoryId}", 2))
           .andExpect(status().isOk())
           .andExpect(view().name("catalog/productsList"))
           .andExpect(MockMvcResultMatchers.model()
                                           .attribute("products", hasSize(9)))
        ;
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void viewProductById() throws Exception {

        mvc.perform(get(url + "/categories/{productId}", 1))
           .andExpect(view().name("catalog/productView"))
           .andExpect(model().attribute("product", hasProperty("name", equalTo("Florinda"))))
           .andExpect(status().isOk());
    }
}