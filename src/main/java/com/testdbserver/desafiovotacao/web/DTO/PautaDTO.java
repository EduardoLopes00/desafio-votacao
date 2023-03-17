package com.testdbserver.desafiovotacao.web.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.testdbserver.desafiovotacao.data.models.Associate;
import com.testdbserver.desafiovotacao.data.models.Pauta;
import com.testdbserver.desafiovotacao.data.models.Section;
import jakarta.annotation.Nonnull;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PautaDTO {
    @JsonProperty
    @Nonnull
    private UUID id;

    @JsonProperty
    @Nonnull
    private String subject;

    @JsonProperty
    private String description;


    public Pauta toModel() {
        return Pauta.builder().id(this.id).subject(this.subject).description(this.description).build();
    }

    public Pauta toModel(List<Section> sectionList) {
        return Pauta.builder().id(this.id).subject(this.subject).description(this.description).sections(sectionList).build();
    }

    public static PautaDTO fromModel(Pauta pauta) {
        return PautaDTO.builder().id(pauta.getId()).subject(pauta.getSubject()).description(pauta.getDescription()).build();
    }
}
