package com.testdbserver.desafiovotacao.controllers;

import com.testdbserver.desafiovotacao.infra.exceptions.InvalidDataException;
import com.testdbserver.desafiovotacao.services.VoteService;
import com.testdbserver.desafiovotacao.utils.TestUtilsFunctions;
import com.testdbserver.desafiovotacao.utils.mocks.VoteMocks;
import com.testdbserver.desafiovotacao.web.DTO.VoteDTO;
import com.testdbserver.desafiovotacao.web.controllers.VoteController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(VoteController.class)
public class VoteControllerTest extends BasicControllerTest{
    @MockBean
    VoteService voteService;

    protected VoteControllerTest() {
        super("/vote");
    }

    @Test
    @WithMockUser
    public void shouldReturn200_WhenVoteCorrectly() throws Exception {
        mockMvc.perform(post(basePath)
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtilsFunctions.convertObjectToJSON(VoteMocks.DEFAULT_VOTE_OBJ_OPTION_YES())))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser
    public void shouldReturn500_WhenVoteAlreadyHappenedWithThePassedAssociateAndSection() throws Exception {
        VoteDTO voteTestingUT = VoteMocks.DEFAULT_VOTE_OBJ_OPTION_YES();

        doThrow(new InvalidDataException("Associate already voted in this session.")).when(voteService).vote(any(VoteDTO.class));

        mockMvc.perform(post(basePath)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtilsFunctions.convertObjectToJSON(voteTestingUT)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value("Associate already voted in this session."));
    }
}
