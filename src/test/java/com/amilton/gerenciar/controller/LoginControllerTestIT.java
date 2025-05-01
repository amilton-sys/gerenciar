package com.amilton.gerenciar.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "spring.security.oauth2.client.registration.google.client-id=fake-client-id",
        "spring.security.oauth2.client.registration.google.client-secret=fake-client-secret"
})
public class LoginControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testLoginPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("pages/login"))
                .andExpect(xpath("//h1").string("Gerenciar"))
                .andExpect(xpath("//p").string("Seja bem vindo. Faça seu login!"))
                .andExpect(xpath("//a/@href").string("/oauth2/authorization/google"))
                .andExpect(xpath("//img/@src").string("img/google.png"))
                .andExpect(xpath("//a").string(containsString("Login com Google")));
    }

    @Test
    public void testLoginWithGoogleRedirect() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/login"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(content().string(containsString("/oauth2/authorization/google")));
    }
}
