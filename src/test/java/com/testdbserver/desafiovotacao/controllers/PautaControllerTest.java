package com.testdbserver.desafiovotacao.controllers;

import com.testdbserver.desafiovotacao.data.models.Pauta;
import com.testdbserver.desafiovotacao.infra.exceptions.NotFoundException;
import com.testdbserver.desafiovotacao.services.PautaService;
import com.testdbserver.desafiovotacao.utils.TestUtilsFunctions;
import com.testdbserver.desafiovotacao.utils.mocks.PautaMocks;
import com.testdbserver.desafiovotacao.web.DTO.PautaDTO;
import com.testdbserver.desafiovotacao.web.controllers.PautaController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PautaController.class)
public class PautaControllerTest extends BasicControllerTest {
    @MockBean
    private PautaService pautaService;

    protected PautaControllerTest() {
        super("/pauta");
    }
    @Test
    @WithMockUser
    public void shouldReturn200_WhenRequestGetPautaByIdIsCalledWithExistingId() throws Exception {
        when(pautaService.getPautaById(PautaMocks.DEFAULT_PAUTA_ID)).thenReturn(PautaMocks.DEFAULT_PAUTA());

        mockMvc.perform(get(basePath + "/{id}", PautaMocks.DEFAULT_PAUTA_ID))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void shouldReturn404_WhenRequestGetPautaByIdIsCalledWithNonexistentId() throws Exception {
        UUID nonexistentPautaId = UUID.fromString("e12c91e6-c617-464b-9cd1-9c8fbd76a6b6");

        when(pautaService.getPautaById(nonexistentPautaId)).thenThrow(new NotFoundException(nonexistentPautaId.toString()));

        mockMvc.perform(get(basePath + "/{id}", nonexistentPautaId))
                .andExpect(status().isNotFound()).andExpect(jsonPath("$.message").value("Item not found for ID " + nonexistentPautaId));
    }

    @Test
    @WithMockUser
    public void shouldReturn201_WhenRequestCreatePautaWithValidData() throws Exception {
        Pauta testingPauta = PautaMocks.DEFAULT_PAUTA();

        when (pautaService.createPauta(any(PautaDTO.class))).thenReturn(testingPauta);

        mockMvc.perform(post(basePath)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtilsFunctions.convertObjectToJSON(PautaDTO.fromModel(testingPauta)))).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.subject").value(testingPauta.getSubject()))
                .andExpect(jsonPath("$.description").value(testingPauta.getDescription()));
    }
}
