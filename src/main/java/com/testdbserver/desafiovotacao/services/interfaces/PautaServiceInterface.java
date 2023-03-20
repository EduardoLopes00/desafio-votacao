package com.testdbserver.desafiovotacao.services.interfaces;

import com.testdbserver.desafiovotacao.data.models.Pauta;
import com.testdbserver.desafiovotacao.web.DTO.PautaDTO;

import java.util.UUID;

public interface PautaServiceInterface {
    public Pauta getPautaById(UUID id);

    public Pauta createPauta(PautaDTO pautaDTO);
}
