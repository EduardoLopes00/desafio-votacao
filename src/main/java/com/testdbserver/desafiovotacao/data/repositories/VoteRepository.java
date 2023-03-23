 package com.testdbserver.desafiovotacao.data.repositories;

import com.testdbserver.desafiovotacao.data.models.Vote;
import com.testdbserver.desafiovotacao.web.DTO.VoteBySectionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface VoteRepository extends JpaRepository<Vote, UUID> {
    public Vote findFirstBySectionIdAndAssociateId(UUID sectionId, UUID associateId);

    @Query(value = "SELECT v.id, a.email, a.cpf, v.vote_option, v.voted_at " +
            "         FROM vote v " +
            "        INNER JOIN associate a ON (v.associate_id = a.id) " +
            "        INNER JOIN section   s ON (v.section_id   = s.id)  " +
            "        WHERE v.section_id = :sectionId", nativeQuery = true)
    public List<VoteBySectionDTO> getVotesBySection(UUID sectionId);
}
