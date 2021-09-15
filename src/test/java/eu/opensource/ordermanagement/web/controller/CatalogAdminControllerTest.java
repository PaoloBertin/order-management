package eu.opensource.ordermanagement.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class CatalogAdminControllerTest {

    @Autowired
    private MockMvc mvc;

    private final String url = "/admin/catalog";

    @Test
    void viewCreateProductFormTest() throws Exception {

        mvc.perform(get(url + "/categories/products").param("form", "true")
                                                     .with(httpBasic("admin@email.com", "admin")))
           .andExpect(model().attribute("productForm", notNullValue()))
           .andExpect(view().name("catalog/createProduct"))
           .andExpect(status().isOk());
    }

    @Test
    void createProductTest() throws Exception {

        mvc.perform(post(url + "/categories/products").param("name", "Quattro Stagioni")
                                                      .param("productCode", "P0030").param("categoryName", "Pizze")
                                                      .with(httpBasic("admin@email.com", "admin")))
           .andExpect(flash().attribute("message", hasProperty("type", equalTo("success"))))
           .andExpect(redirectedUrl("/"));
    }

    @Test
    void viewProducts() {

    }

    @Test
    void editProductForm() {

    }

    @Test
    void editProduct() {

    }
}