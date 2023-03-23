package com.testdbserver.desafiovotacao.web.controllers;

import com.testdbserver.desafiovotacao.services.VoteService;
import com.testdbserver.desafiovotacao.web.DTO.VoteDTO;
import com.testdbserver.desafiovotacao.web.controllers.interfaces.VoteControllerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("vote")
public class VoteController implements VoteControllerInterface {
    @Autowired
    VoteService voteService;

    @Override
    @PostMapping
    public ResponseEntity<String> vote(@RequestBody VoteDTO voteDTO) {
        voteService.vote(voteDTO);

        return ResponseEntity.status(201).body("Vote registered successfully");
    }


}
