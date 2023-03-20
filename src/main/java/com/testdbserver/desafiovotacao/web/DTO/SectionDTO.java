package com.testdbserver.desafiovotacao.web.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.testdbserver.desafiovotacao.data.models.Pauta;
import com.testdbserver.desafiovotacao.data.models.Section;
import com.testdbserver.desafiovotacao.infra.enums.SectionStatusEnum;
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
    private UUID pautaId;

    @JsonProperty
    private SectionStatusEnum status;

    @JsonProperty
    @JsonIgnore
    private Date createdAt;

    @JsonProperty
    private Date dtStart;

    @JsonProperty
    private int duration;

    public Section toModel() {
        return Section.builder().id(this.id).createdAt(this.createdAt).dtStart(this.dtStart).status(this.status).duration(this.duration).build();
    }

    public Section toModel(Pauta pauta) {
        return Section.builder().id(this.id).createdAt(this.createdAt).dtStart(this.dtStart).status(this.status).duration(this.duration).pauta(pauta).build();
    }

    public static SectionDTO fromModel(Section section) {
        return SectionDTO.builder().id(section.getId()).createdAt(section.getCreatedAt()).dtStart(section.getDtStart()).pautaId(section.getPauta().getId()).duration(section.getDuration()).status(section.getStatus()).build();
    }
}
