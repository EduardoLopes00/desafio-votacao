package com.testdbserver.desafiovotacao.web.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.testdbserver.desafiovotacao.infra.enums.VoteOptionEnum;

import java.util.UUID;

public class VoteBySectionDTO {
    @JsonProperty
    private UUID voteId;

    @JsonProperty
    private String associateEmail;

    @JsonProperty
    private String associateCpf;

    @JsonProperty
    private VoteOptionEnum voteOption;

    @JsonProperty
    private String pautaSubject;





}
