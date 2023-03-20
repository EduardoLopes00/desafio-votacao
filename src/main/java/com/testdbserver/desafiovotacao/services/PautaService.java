package com.testdbserver.desafiovotacao.services;

import com.testdbserver.desafiovotacao.data.models.Pauta;
import com.testdbserver.desafiovotacao.data.repositories.PautaRepository;
import com.testdbserver.desafiovotacao.infra.exceptions.NotFoundException;
import com.testdbserver.desafiovotacao.services.interfaces.PautaServiceInterface;
import com.testdbserver.desafiovotacao.web.DTO.PautaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service

public class PautaService implements PautaServiceInterface {
    @Autowired
    PautaRepository pautaRepository;

    @Override
    public Pauta getPautaById(UUID id) {
        return pautaRepository.findById(id).orElseThrow(() -> new NotFoundException(id.toString())) ;
    }

    @Override
    public Pauta createPauta(PautaDTO pautaDTO) {


        Pauta newPauta = pautaRepository.saveAndFlush(pautaDTO.toModel());

        return newPauta;
    }
}
