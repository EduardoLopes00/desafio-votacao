package com.testdbserver.desafiovotacao.controllers;

import com.testdbserver.desafiovotacao.data.models.Associate;
import com.testdbserver.desafiovotacao.infra.exceptions.AlreadyExistsException;
import com.testdbserver.desafiovotacao.infra.exceptions.NotFoundException;
import com.testdbserver.desafiovotacao.services.AssociateService;
import com.testdbserver.desafiovotacao.utils.TestUtilsFunctions;
import com.testdbserver.desafiovotacao.utils.mocks.AssociateMocks;
import com.testdbserver.desafiovotacao.web.DTO.AssociateDTO;
import com.testdbserver.desafiovotacao.web.controllers.AssociateController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;
import java.util.UUID;

@WebMvcTest(AssociateController.class)
public class AssociateControllerTest {

    private final String basePath = "/associate";

    @MockBean
    private AssociateService associateService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturn200_WhenGetAssociateByIdThatExists() throws Exception {
        when(associateService.getAssociateById(AssociateMocks.DEFAULT_ASSOCIATE_ID)).thenReturn(AssociateMocks.DEFAULT_ASSOCIATE());

        mockMvc.perform(get(basePath + "/{id}", AssociateMocks.DEFAULT_ASSOCIATE_ID))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturn404_WhenGetAssociateByIdThatNotExists() throws Exception {
        UUID nonexistentAssociateId = UUID.fromString("e12c92e6-c617-464b-9cd1-9c8fbd76a6b6");

        when(associateService.getAssociateById(nonexistentAssociateId)).thenThrow(new NotFoundException(nonexistentAssociateId.toString()));

        mockMvc.perform(get(basePath + "/{id}", nonexistentAssociateId))
                .andExpect(status().isNotFound()).andExpect(jsonPath("$.message").value("Item not found for ID " + nonexistentAssociateId));
    }

    @Test
    public void shouldReturn201_WhenCreateANewAssociate() throws Exception {
        Associate testingAssociate = AssociateMocks.DEFAULT_ASSOCIATE();

        when(associateService.createAssociate(any(AssociateDTO.class))).thenReturn(testingAssociate);

        mockMvc.perform(post(basePath)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtilsFunctions.convertObjectToJSON(AssociateDTO.fromModel(testingAssociate)))).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.cpf").value(testingAssociate.getCpf()))
                .andExpect(jsonPath("$.email").value(testingAssociate.getEmail()));
    }

    @Test
    public void shouldReturn500_WhenTryToCreateAssociateWithAnAlreadyExistsCPF() throws Exception {
        Associate testingAssociate = AssociateMocks.DEFAULT_ASSOCIATE();

        when(associateService.createAssociate(any (AssociateDTO.class))).thenThrow(new AlreadyExistsException(testingAssociate.getCpf()));

        mockMvc.perform(post(basePath)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtilsFunctions.convertObjectToJSON(AssociateDTO.fromModel(testingAssociate))))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value("The system couldn't complete the action because the item already exists for data " + testingAssociate.getCpf()));
    }

    @Test
    public void shouldReturn500_WhenTryToCreateAssociateWithAnAlreadyExistsEmail() throws Exception {
        Associate testingAssociate = AssociateMocks.DEFAULT_ASSOCIATE();

        when(associateService.createAssociate(any (AssociateDTO.class))).thenThrow(new AlreadyExistsException(testingAssociate.getEmail()));

        mockMvc.perform(post(basePath)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtilsFunctions.convertObjectToJSON(AssociateDTO.fromModel(testingAssociate))))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value("The system couldn't complete the action because the item already exists for data " + testingAssociate.getEmail()));
    }
}










































