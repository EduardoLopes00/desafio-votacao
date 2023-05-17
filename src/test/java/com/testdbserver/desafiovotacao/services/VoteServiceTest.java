package com.testdbserver.desafiovotacao.services;

import com.testdbserver.desafiovotacao.data.models.Vote;
import com.testdbserver.desafiovotacao.data.repositories.VoteRepository;
import com.testdbserver.desafiovotacao.infra.exceptions.AlreadyExistsException;
import com.testdbserver.desafiovotacao.utils.mocks.VoteMocks;
import com.testdbserver.desafiovotacao.web.DTO.VoteDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class VoteServiceTest {

    @InjectMocks
    VoteService voteService;

    @Mock
    VoteRepository voteRepository;

    @Test
    public void shoudVoteCorrectly_WhenVotingWithValidData() {
        VoteDTO votingDataUT = VoteMocks.DEFAULT_VOTE_OBJ_OPTION_YES();

        when(voteRepository.findFirstBySectionIdAndAssociateId(votingDataUT.getSectionId(), votingDataUT.getAssociateId())).thenReturn(null);

        Assertions.assertDoesNotThrow(() -> voteService.vote(votingDataUT));
        verify(voteRepository, times(1)).saveAndFlush(any(Vote.class));
    }

    @Test
    public void shoudThrowInternalServerError_WhenVoteAlreadyHappenedWithThePassedAssociateAndSection() {
        VoteDTO votingDataUT = VoteMocks.DEFAULT_VOTE_OBJ_OPTION_YES();

        when(voteRepository.findFirstBySectionIdAndAssociateId(votingDataUT.getSectionId(), votingDataUT.getAssociateId())).thenReturn(VoteMocks.DEFAULT_VOTE_OBJ_OPTION_ABSTENTION().toModel());


        verify(voteRepository, times(0)).saveAndFlush(any(Vote.class));
        assertThrows(AlreadyExistsException.class, () -> voteService.vote(votingDataUT));
    }
}
