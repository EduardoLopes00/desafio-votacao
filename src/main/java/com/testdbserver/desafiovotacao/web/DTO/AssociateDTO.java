package com.testdbserver.desafiovotacao.web.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.testdbserver.desafiovotacao.data.models.Associate;
import com.testdbserver.desafiovotacao.utils.UtilsFunctions;
import jakarta.annotation.Nonnull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssociateDTO {

    @JsonProperty
    @Nonnull
    private UUID id;

    @JsonProperty
    @Nonnull
    private String cpf;

    @JsonProperty
    private String email;

    public Associate toModel() {
        return Associate.builder().id(this.id).cpf(UtilsFunctions.removeDotsHyphensSpacesFromString(this.cpf)).email(this.email).build();
    }

    public static AssociateDTO fromModel(Associate associate) {
        return AssociateDTO.builder().id(associate.getId()).cpf(associate.getCpf()).email(associate.getEmail()).build();
    }
}
