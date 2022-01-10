package eu.opensource.ordermanagement.web.controller;

import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
// @SpringBootTest
class CatalogAdminControllerTest {

    @Autowired
    private MockMvc mvc;

    private final String url = "/admin/catalog";

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
//    @Test
    void viewCreateProductFormTest() throws Exception {

        mvc.perform(get(url + "/categories/products").param("form", "true")
                                                     .with(httpBasic("admin@email.com", "admin")))
           .andExpect(model().attribute("productForm", notNullValue()))
           .andExpect(view().name("catalog/createProduct"))
           .andExpect(status().isOk());
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
//    @Test
    void createProductTest() throws Exception {

        mvc.perform(post(url + "/categories/products").param("name", "Quattro Stagioni")
                                                      .param("productCode", "P0030")
                                                      .param("categoryName", "Pizze")
                                                      .with(httpBasic("admin@email.com", "admin")))
           .andExpect(flash().attribute("message", hasProperty("type", equalTo("success"))))
           .andExpect(redirectedUrl("/"));
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
//    @Test
    void viewProductsByCategoryTest() throws Exception {

        mvc.perform(get(url + "/{categoryId}/products", 1L).with(httpBasic("admin@email.com", "admin")))
           .andExpect((status().isOk()))
           .andExpect(model().attribute("categoryName", equalTo("Specialità")))
           .andExpect(model().attribute("categories", IsCollectionWithSize.hasSize(5)))
           .andExpect(model().attribute("products", IsCollectionWithSize.hasSize(3)))

        ;
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
//    @Test
    void viewEditProductFormTest() throws Exception {

        mvc.perform(get(url + "/categories/{productId}", 1L).with(httpBasic("admin@email.com", "admin")))
           .andExpect(model().attribute("productForm", hasProperty("id", notNullValue())))
           .andExpect(model().attribute("categories", IsCollectionWithSize.hasSize(5)))
           .andExpect(view().name("catalog/editProduct"))
        ;
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
//    @Test
    void editProductTest() throws Exception {

        mvc.perform(put(url + "/categories/products", 1L).param("id", "1")
                                                         .param("price", "9.50")
                                                         .param("productCode", "P0001")
                                                         .param("categoryName", "Pizze")
                                                         .with(httpBasic("admin@email.com", "admin")))
           .andExpect(flash().attribute("message", hasProperty("type", equalTo("success"))))
        ;
    }
}