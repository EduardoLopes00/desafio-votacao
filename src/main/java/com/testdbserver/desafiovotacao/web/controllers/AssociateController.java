package com.testdbserver.desafiovotacao.web.controllers;

import com.testdbserver.desafiovotacao.data.models.Associate;
import com.testdbserver.desafiovotacao.services.AssociateService;
import com.testdbserver.desafiovotacao.web.DTO.AssociateDTO;
import com.testdbserver.desafiovotacao.web.controllers.interfaces.AssociateControllerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("associate")
public class AssociateController implements AssociateControllerInterface {

    @Autowired
    private AssociateService associateService;

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<Associate> getById(@PathVariable UUID id) {
        Associate associate = associateService.getAssociateById(id);

        return ResponseEntity.ok(associate);
    }
}
