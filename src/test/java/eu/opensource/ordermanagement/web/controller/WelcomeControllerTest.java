package eu.opensource.ordermanagement.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@AutoConfigureMockMvc
@SpringBootTest
class WelcomeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void homePageTest() throws Exception {

        mvc.perform(get("/"))
           .andExpect(status().isOk())
           .andExpect(view().name("welcome"))
        ;
    }
}