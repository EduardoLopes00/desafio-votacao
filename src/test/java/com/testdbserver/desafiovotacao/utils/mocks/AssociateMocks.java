package com.testdbserver.desafiovotacao.utils.mocks;

import com.testdbserver.desafiovotacao.data.models.Associate;
import com.testdbserver.desafiovotacao.web.DTO.AssociateDTO;

import java.util.UUID;

public class AssociateMocks {

    public static UUID DEFAULT_ASSOCIATE_ID = UUID.fromString("85a1442d-10f3-4549-93f9-b59a6caf75f5");
    public static String DEFAULT_ASSOCIATE_CPF = "90473686147";

    public static Associate DEFAULT_ASSOCIATE() {
        return Associate.builder().id(DEFAULT_ASSOCIATE_ID).cpf(DEFAULT_ASSOCIATE_CPF).email("test222test@gmail.com").build();
    }
}
