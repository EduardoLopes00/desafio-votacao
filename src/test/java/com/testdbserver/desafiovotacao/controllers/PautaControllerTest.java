package com.testdbserver.desafiovotacao.controllers;

import com.testdbserver.desafiovotacao.infra.exceptions.NotFoundException;
import com.testdbserver.desafiovotacao.services.PautaService;
import com.testdbserver.desafiovotacao.utils.mocks.PautaMocks;
import com.testdbserver.desafiovotacao.web.controllers.PautaController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PautaController.class)

public class PautaControllerTest {
    private final String basePath = "/pauta";

    @MockBean
    private PautaService pautaService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturn200_WhenRequestGetPautaByIdIsCalledWithExistingId() throws Exception {
        when(pautaService.getPautaById(PautaMocks.DEFAULT_PAUTA_ID)).thenReturn(PautaMocks.DEFAULT_PAUTA());

        mockMvc.perform(get(basePath + "/{id}", PautaMocks.DEFAULT_PAUTA_ID))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturn404_WhenRequestGetAssociateByIdIsCalledWithNonexistentId() throws Exception {
        UUID nonexistentPautaId = UUID.fromString("e12c91e6-c617-464b-9cd1-9c8fbd76a6b6");

        when(pautaService.getPautaById(nonexistentPautaId)).thenThrow(new NotFoundException(nonexistentPautaId.toString()));

        mockMvc.perform(get(basePath + "/{id}", nonexistentPautaId))
                .andExpect(status().isNotFound()).andExpect(jsonPath("$.message").value("Item not found for ID " + nonexistentPautaId));
    }
}
