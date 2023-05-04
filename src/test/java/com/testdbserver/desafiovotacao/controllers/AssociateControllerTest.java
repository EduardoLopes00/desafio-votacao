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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

@WebMvcTest(AssociateController.class)
public class AssociateControllerTest extends BasicControllerTest {
    @MockBean
    private AssociateService associateService;

    protected AssociateControllerTest() {
        super("/associate");
    }

    @Test
    @WithMockUser
    public void shouldReturn200_WhenRequestGetAssociateByIdIsCalledWithExistingId() throws Exception {
        when(associateService.getAssociateById(AssociateMocks.DEFAULT_ASSOCIATE_ID)).thenReturn(AssociateMocks.DEFAULT_ASSOCIATE());

        mockMvc.perform(get(basePath + "/{id}", AssociateMocks.DEFAULT_ASSOCIATE_ID)).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void shouldReturn404_WhenRequestGetAssociateByIdIsCalledWithNonexistentId() throws Exception {
        UUID nonexistentAssociateId = UUID.fromString("e12c92e6-c617-464b-9cd1-9c8fbd76a6b6");

        when(associateService.getAssociateById(nonexistentAssociateId)).thenThrow(new NotFoundException(nonexistentAssociateId.toString()));

        mockMvc.perform(get(basePath + "/{id}", nonexistentAssociateId))
                .andExpect(status().isNotFound()).andExpect(jsonPath("$.message").value("Item not found for ID " + nonexistentAssociateId));
    }
}










































