package com.testdbserver.desafiovotacao.web.DTO;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RegisterRequestDTO {
    @JsonProperty
    @Nonnull
    AssociateDTO associateDTO;

    @JsonProperty
    @Nonnull
    private String password;

    @JsonProperty
    @Nonnull
    private String passwordConfirm;
}
