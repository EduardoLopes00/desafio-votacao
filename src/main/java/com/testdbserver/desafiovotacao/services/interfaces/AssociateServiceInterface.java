package com.testdbserver.desafiovotacao.services.interfaces;

import com.testdbserver.desafiovotacao.data.models.Associate;
import com.testdbserver.desafiovotacao.web.DTO.AssociateDTO;

import java.util.UUID;

public interface AssociateServiceInterface {
    public Associate getAssociateById(UUID id);

    public Associate createAssociate(AssociateDTO associateDTO);
}
