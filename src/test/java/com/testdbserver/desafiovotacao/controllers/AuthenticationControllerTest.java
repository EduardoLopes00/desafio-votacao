package com.testdbserver.desafiovotacao.controllers;

import com.testdbserver.desafiovotacao.infra.exceptions.AlreadyExistsException;
import com.testdbserver.desafiovotacao.infra.exceptions.InvalidDataException;
import com.testdbserver.desafiovotacao.services.AuthenticationService;
import com.testdbserver.desafiovotacao.utils.TestUtilsFunctions;
import com.testdbserver.desafiovotacao.utils.mocks.AuthenticationMocks;
import com.testdbserver.desafiovotacao.web.DTO.LoginRequestDTO;
import com.testdbserver.desafiovotacao.web.DTO.LoginResponseDTO;
import com.testdbserver.desafiovotacao.web.DTO.RegisterRequestDTO;
import com.testdbserver.desafiovotacao.web.controllers.AuthenticationController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthenticationController.class)

public class AuthenticationControllerTest extends BasicControllerTest {
    @MockBean
    private AuthenticationService authenticationService;

    protected AuthenticationControllerTest() {
        super("/auth");
    }

    @Test
    public void shouldReturn200_whenLoginInWithAValidUser() throws Exception {
        LoginResponseDTO loginResponseDTOTestingObject = AuthenticationMocks.DEFAULT_LOGIN_RESPONSE_OBJ();

        when(authenticationService.login(any(LoginRequestDTO.class))).thenReturn(loginResponseDTOTestingObject);

        mockMvc.perform(post(basePath + "/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtilsFunctions.convertObjectToJSON(AuthenticationMocks.DEFAULT_LOGIN_REQUEST_OBJ())))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value(AuthenticationMocks.DEFAULT_TOKEN));

    }

    @Test
    public void shouldReturn201_WhenRequestRegisterAssociateWithValidData() throws Exception {
        LoginResponseDTO registerResponseDTOTestingObject = AuthenticationMocks.DEFAULT_LOGIN_RESPONSE_OBJ();


        when(authenticationService.register(any(RegisterRequestDTO.class))).thenReturn(registerResponseDTOTestingObject);

        mockMvc.perform(post(basePath + "/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtilsFunctions.convertObjectToJSON(AuthenticationMocks.DEFAULT_REGISTER_REQUEST_OBJ()))).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.associateDTO").isNotEmpty());
    }

    @Test
    public void shouldReturn500_WhenRequestRegisterAssociateIsCalledWithExistingCPF() throws Exception {
        RegisterRequestDTO testingAssociate = AuthenticationMocks.DEFAULT_REGISTER_REQUEST_OBJ();

        when(authenticationService.register(any (RegisterRequestDTO.class))).thenThrow(new AlreadyExistsException(testingAssociate.getAssociateDTO().getCpf(), ""));

        mockMvc.perform(post(basePath + "/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtilsFunctions.convertObjectToJSON(testingAssociate)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value("The system couldn't complete the action because the item already exists for data " + testingAssociate.getAssociateDTO().getCpf()));
    }


    @Test
    public void shouldReturn500_WhenRequestRegisterAssociateIsCalledWithExistingEmail() throws Exception {
        RegisterRequestDTO testingAssociate = AuthenticationMocks.DEFAULT_REGISTER_REQUEST_OBJ();

        when(authenticationService.register(any (RegisterRequestDTO.class))).thenThrow(new AlreadyExistsException(testingAssociate.getAssociateDTO().getEmail(), ""));

        mockMvc.perform(post(basePath + "/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtilsFunctions.convertObjectToJSON(testingAssociate)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value("The system couldn't complete the action because the item already exists for data " + testingAssociate.getAssociateDTO().getEmail()));
    }

    @Test
    public void shouldReturn500_WhenRequestRegisterAssociateIsCalledWithDifferentPasswords() throws Exception {
        RegisterRequestDTO testingAssociate = AuthenticationMocks.DEFAULT_REGISTER_REQUEST_OBJ();
        testingAssociate.setPasswordConfirm(testingAssociate.getPassword() + "123");

        when(authenticationService.register(any (RegisterRequestDTO.class))).thenThrow(new InvalidDataException("Password doesn't match"));

        mockMvc.perform(post(basePath + "/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtilsFunctions.convertObjectToJSON(testingAssociate)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value("Password doesn't match"));
    }
}
