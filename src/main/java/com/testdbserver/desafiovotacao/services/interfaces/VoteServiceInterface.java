package com.testdbserver.desafiovotacao.services.interfaces;

import com.testdbserver.desafiovotacao.data.models.Vote;
import com.testdbserver.desafiovotacao.web.DTO.VoteBySectionDTO;
import com.testdbserver.desafiovotacao.web.DTO.VoteDTO;

import java.util.List;
import java.util.UUID;

public interface VoteServiceInterface {
    public void vote(VoteDTO voteDTO);

    public List<VoteBySectionDTO> getVotesBySection(UUID sectionId);
}
