package com.testdbserver.desafiovotacao.web.controllers.interfaces;

import com.testdbserver.desafiovotacao.web.DTO.VoteDTO;
import org.springframework.http.ResponseEntity;

public interface VoteControllerInterface {
    public ResponseEntity<String> vote(VoteDTO voteDTO);
}
