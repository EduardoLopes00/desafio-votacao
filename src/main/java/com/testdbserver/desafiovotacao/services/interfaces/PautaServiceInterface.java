package com.testdbserver.desafiovotacao.services.interfaces;

import com.testdbserver.desafiovotacao.data.models.Pauta;

import java.util.UUID;

public interface PautaServiceInterface {
    public Pauta getPautaById(UUID id);
}
