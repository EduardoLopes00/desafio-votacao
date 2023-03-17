package com.testdbserver.desafiovotacao.web.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.testdbserver.desafiovotacao.data.models.Pauta;
import com.testdbserver.desafiovotacao.data.models.Section;
import jakarta.annotation.Nonnull;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SectionDTO {
    @JsonProperty
    @Nonnull
    private UUID id;

    @JsonProperty
    private UUID pauta_id;

    @JsonProperty
    private int status;

    @JsonProperty
    private Date createdAt;

    @JsonProperty
    private int duration;

    public Section toModel() {
        return Section.builder().id(this.id).createdAt(this.createdAt).status(this.status).duration(this.duration).build();
    }

    public Section toModel(Pauta pauta) {
        return Section.builder().id(this.id).createdAt(this.createdAt).status(this.status).duration(this.duration).pauta(pauta).build();
    }

    public static SectionDTO fromModel(Section section) {
        return SectionDTO.builder().id(section.getId()).createdAt(section.getCreatedAt()).pauta_id(section.getPauta().getId()).status(section.getStatus()).build();
    }

}
