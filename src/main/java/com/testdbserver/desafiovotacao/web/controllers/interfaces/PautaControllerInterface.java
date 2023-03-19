package com.testdbserver.desafiovotacao.web.controllers.interfaces;

import com.testdbserver.desafiovotacao.data.models.Pauta;
import com.testdbserver.desafiovotacao.web.DTO.PautaDTO;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface PautaControllerInterface {
    public ResponseEntity<Pauta> getById(UUID id);
}
