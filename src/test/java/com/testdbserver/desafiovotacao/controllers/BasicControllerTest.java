package com.testdbserver.desafiovotacao.controllers;

import com.testdbserver.desafiovotacao.infra.jwt.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public abstract class BasicControllerTest<T> {
    protected String basePath = "";

    protected BasicControllerTest(String basePath) {
        this.basePath = basePath;
    }

    @MockBean
    protected JwtService jwtService;
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @BeforeEach()
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
}
