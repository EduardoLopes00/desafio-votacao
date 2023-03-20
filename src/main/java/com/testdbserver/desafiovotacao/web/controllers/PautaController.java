package com.testdbserver.desafiovotacao.web.controllers;

import com.testdbserver.desafiovotacao.data.models.Associate;
import com.testdbserver.desafiovotacao.data.models.Pauta;
import com.testdbserver.desafiovotacao.services.PautaService;
import com.testdbserver.desafiovotacao.web.DTO.PautaDTO;
import com.testdbserver.desafiovotacao.web.controllers.interfaces.PautaControllerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("pauta")
public class PautaController implements PautaControllerInterface {

    @Autowired
    private PautaService pautaService;

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<Pauta> getById(@PathVariable UUID id) {
        Pauta pauta = pautaService.getPautaById(id);

        return ResponseEntity.ok(pauta);
    }

    @Override
    @PostMapping(value = "")
    public ResponseEntity<Pauta> createPauta(@RequestBody PautaDTO pautaDTO) {
        Pauta newPauta = pautaService.createPauta(pautaDTO);

        return ResponseEntity.status(201).body(newPauta);
    }


}
