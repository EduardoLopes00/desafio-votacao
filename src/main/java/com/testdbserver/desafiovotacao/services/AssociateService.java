package com.testdbserver.desafiovotacao.services;

import com.testdbserver.desafiovotacao.data.models.Associate;
import com.testdbserver.desafiovotacao.data.repositories.AssociateRepository;
import com.testdbserver.desafiovotacao.infra.exceptions.AlreadyExistsException;
import com.testdbserver.desafiovotacao.infra.exceptions.NotFoundException;
import com.testdbserver.desafiovotacao.services.interfaces.AssociateServiceInterface;
import com.testdbserver.desafiovotacao.web.DTO.AssociateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AssociateService implements AssociateServiceInterface {

    @Autowired
    private AssociateRepository associateRepository;

    @Override
    public Associate getAssociateById(UUID id) {
        return associateRepository.findById(id).orElseThrow(() -> new NotFoundException(id.toString())) ;
    }

    @Override
    public Associate createAssociate(AssociateDTO associateDTO) {
        Associate existsAssociateCpf = associateRepository.findFirstByCpf(associateDTO.getCpf());

        if (existsAssociateCpf != null) throw new AlreadyExistsException(existsAssociateCpf.getCpf());

        Associate newAssociate = associateRepository.saveAndFlush(associateDTO.toModel());

        return newAssociate;
    }
}
