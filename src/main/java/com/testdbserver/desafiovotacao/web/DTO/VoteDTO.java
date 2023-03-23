package com.testdbserver.desafiovotacao.web.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.testdbserver.desafiovotacao.data.models.Associate;
import com.testdbserver.desafiovotacao.data.models.Pauta;
import com.testdbserver.desafiovotacao.data.models.Section;
import com.testdbserver.desafiovotacao.data.models.Vote;
import com.testdbserver.desafiovotacao.infra.enums.VoteOptionEnum;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VoteDTO {
    @Nonnull
    @JsonProperty
    private UUID associateId;

    @Nonnull
    @JsonProperty
    private UUID sectionId;

    @JsonProperty
    @JsonIgnore
    private Date votedAt;

    @JsonProperty
    private VoteOptionEnum voteOption;

    public Vote toModel() {
        return Vote.builder().votedAt(new Date()).sectionId(sectionId).associateId(associateId).voteOption(voteOption).build();
    }

    public static VoteDTO fromModel(Vote vote) {
        return VoteDTO.builder().votedAt(vote.getVotedAt()).sectionId(vote.getSection().getId()).associateId(vote.getAssociate().getId()).voteOption(vote.getVoteOption()).build();
    }

}
