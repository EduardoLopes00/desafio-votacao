package com.testdbserver.desafiovotacao.services;

import com.testdbserver.desafiovotacao.data.enums.AssociateStatusEnum;
import com.testdbserver.desafiovotacao.data.enums.AssociateRolesEnum;
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
    public Associate createAssociate(AssociateDTO associateDTO, String encodedPassword) {
        if (isValidAssociate(associateDTO)) {
            Associate newAssociate = associateDTO.toModel();
            newAssociate.setStatus(AssociateStatusEnum.ACTIVE);
            newAssociate.setRole(AssociateRolesEnum.ROLE_USER);
            newAssociate.setPassword(encodedPassword);

            return associateRepository.saveAndFlush(newAssociate);
        }

        return null;
    }

    @Override
    public Associate createAssociate(AssociateDTO associateDTO) {
        return this.createAssociate(associateDTO, "");
    }

    protected boolean isValidAssociate(AssociateDTO associateDTO) {
        Associate existsAssociateCpf = associateRepository.findFirstByCpf(associateDTO.getCpf());

        if (existsAssociateCpf != null) throw new AlreadyExistsException(existsAssociateCpf.getCpf(), "");


        if (!associateDTO.getEmail().isEmpty()){
            Associate existsAssociateEmail = associateRepository.findFirstByCpf(associateDTO.getEmail());

            if (existsAssociateEmail != null) throw new AlreadyExistsException(existsAssociateEmail.getEmail(), "");
        }

        return true;
    }
}
