package eu.opensource.ordermanagement.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@AutoConfigureMockMvc
@SpringBootTest
class CatalogControllerTest {

    @Autowired
    private MockMvc mvc;

    private String url = "/catalog";

    @Test
    void viewProductByCategoryTest() throws Exception {

        mvc.perform(get(url + "/{categoryId}", 2))
           .andExpect(status().isOk())
           .andExpect(view().name("catalog/productsList"))
           .andExpect(MockMvcResultMatchers.model()
                                           .attribute("products", hasSize(9)))
        ;
    }
}