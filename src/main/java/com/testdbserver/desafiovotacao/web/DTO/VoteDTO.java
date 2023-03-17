package com.testdbserver.desafiovotacao.web.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.testdbserver.desafiovotacao.data.models.Associate;
import com.testdbserver.desafiovotacao.data.models.Pauta;
import com.testdbserver.desafiovotacao.data.models.Section;
import com.testdbserver.desafiovotacao.data.models.Vote;
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
    private UUID id;

    @Nonnull
    @JsonProperty
    private UUID associate_id;

    @Nonnull
    @JsonProperty
    private UUID section_id;

    @Nonnull
    @JsonProperty
    private Date votedAt;

    public Vote toModel(Section section, Associate associate) {
        return Vote.builder().id(this.id).votedAt(this.votedAt).section(section).associate(associate).build();
    }

    public static VoteDTO fromModel(Vote vote) {
        return VoteDTO.builder().id(vote.getId()).votedAt(vote.getVotedAt()).section_id(vote.getSection().getId()).associate_id(vote.getAssociate().getId()).build();
    }

}
