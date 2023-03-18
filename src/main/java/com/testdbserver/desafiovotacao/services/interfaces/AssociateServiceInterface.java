package com.testdbserver.desafiovotacao.services.interfaces;

import com.testdbserver.desafiovotacao.data.models.Associate;
import java.util.UUID;

public interface AssociateServiceInterface {
    public Associate getAssociateById(UUID id);
}
