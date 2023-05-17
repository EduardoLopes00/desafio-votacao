package com.testdbserver.desafiovotacao.utils.mocks;

import com.testdbserver.desafiovotacao.data.enums.AssociateRolesEnum;
import com.testdbserver.desafiovotacao.data.enums.AssociateStatusEnum;
import com.testdbserver.desafiovotacao.data.models.Associate;

import java.util.UUID;

public class AssociateMocks {

    public static UUID DEFAULT_ASSOCIATE_ID = UUID.fromString("88f86027-30bc-4f6a-8f23-733266ebb016");
    public static String DEFAULT_ASSOCIATE_CPF = "90473686147";

    public static Associate DEFAULT_ASSOCIATE() {
        return Associate.builder().id(DEFAULT_ASSOCIATE_ID).fullname("test222 test").status(AssociateStatusEnum.ACTIVE).role(AssociateRolesEnum.ROLE_USER).cpf(DEFAULT_ASSOCIATE_CPF).email("jon@dhoe.com").build();
    }
}
