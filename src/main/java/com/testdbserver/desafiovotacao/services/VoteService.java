package com.testdbserver.desafiovotacao.services;

import com.testdbserver.desafiovotacao.data.models.Vote;
import com.testdbserver.desafiovotacao.data.repositories.VoteRepository;
import com.testdbserver.desafiovotacao.infra.exceptions.AlreadyExistsException;
import com.testdbserver.desafiovotacao.services.interfaces.VoteServiceInterface;
import com.testdbserver.desafiovotacao.web.DTO.VoteBySectionDTO;
import com.testdbserver.desafiovotacao.web.DTO.VoteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VoteService implements VoteServiceInterface {
    @Autowired
    VoteRepository voteRepository;

    public void vote(VoteDTO voteDTO){
        Vote vote = voteRepository.findFirstBySectionIdAndAssociateId(voteDTO.getSectionId(), voteDTO.getAssociateId());


        if (vote != null) {
            throw new AlreadyExistsException("Associate already voted in this session.");
        }

        voteRepository.saveAndFlush(voteDTO.toModel());
    }

    public List<VoteBySectionDTO> getVotesBySection(UUID sectionId) {
        return voteRepository.getVotesBySection(sectionId);
    }
}
