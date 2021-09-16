package eu.opensource.ordermanagement.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class SignUpControllerTest {

    @Autowired
    private MockMvc mvc;

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void signupForm() throws Exception {

        mvc.perform(get("/signup/form"))
           .andExpect(view().name("login/signupForm"))
           .andExpect(status().isOk())
        ;
    }

    @Sql({"/schema-h2.sql", "/data-h2.sql"})
    @Test
    void signupText() throws Exception {

        mvc.perform((post("/signup/new").param("firstname", "paolo")
                                        .param("lastname", "bertin")
                                        .param("email", "bertin@email.com")
                                        .param("password", "paolo")))
           .andExpect(redirectedUrl("/"));
    }
}