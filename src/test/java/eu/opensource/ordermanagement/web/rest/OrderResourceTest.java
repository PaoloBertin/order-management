package eu.opensource.ordermanagement.web.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// @AutoConfigureMockMvc
// @SpringBootTest
class OrderResourceTest {

    @Autowired
    private MockMvc mvc;

    private String url = "/orders";

    // @Test
    void viewOrderById() throws Exception {

        mvc.perform(get(url + "/all/{orderId}", 1L).contentType(MediaType.APPLICATION_JSON)
                                                   .with(user("user").password("user@email.com")
                                                                     .roles("USER")))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.totalAmount", equalTo(169.50)))
        ;
    }

    // @Test
    void viewAllOrders() throws Exception {

        mvc.perform(get(url + "/all/all").contentType(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.length()", equalTo(15)))
        ;
    }

    // @Test
    void viewOrdersCustomer() throws Exception {

        mvc.perform(get(url + "/{customerId}/all", 1L).contentType(MediaType.APPLICATION_JSON))
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.length()", equalTo(7)))
        ;
    }
}