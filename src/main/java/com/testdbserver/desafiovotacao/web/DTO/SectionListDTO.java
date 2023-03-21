package com.testdbserver.desafiovotacao.web.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.testdbserver.desafiovotacao.data.models.Pauta;
import com.testdbserver.desafiovotacao.data.models.Section;
import com.testdbserver.desafiovotacao.infra.enums.SectionStatusEnum;
import jakarta.annotation.Nonnull;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SectionListDTO {
    @JsonProperty
    @Nonnull
    private UUID id;

    @JsonProperty
    private SectionStatusEnum status;

    @JsonProperty
    private Date dtStart;

    @JsonProperty
    private String pautaSubject;

    public static SectionListDTO fromModel(Section section) {
        return SectionListDTO.builder().id(section.getId()).dtStart(section.getDtStart()).pautaSubject(section.getPauta().getSubject()).status(section.getStatus()).build();
    }
}
