package com.testdbserver.desafiovotacao.utils.mocks;

import com.testdbserver.desafiovotacao.data.models.Associate;
import com.testdbserver.desafiovotacao.web.DTO.AssociateDTO;
import com.testdbserver.desafiovotacao.web.DTO.LoginRequestDTO;
import com.testdbserver.desafiovotacao.web.DTO.LoginResponseDTO;
import com.testdbserver.desafiovotacao.web.DTO.RegisterRequestDTO;

public class AuthenticationMocks {
    public static String DEFAULT_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJJc3N1ZXIiOiJJc3N1ZXIiLCJVc2VybmFtZSI6IkphdmFJblVzZSIsImV4cCI6MTY4MzEzMDA2MywiaWF0IjoxNjgzMTMwMDYzfQ.Vmk9HjR_PRPXHhRgNzEcgi8XDkpjKbOmWhmE6jgo8w0";

    public static LoginRequestDTO DEFAULT_LOGIN_REQUEST_OBJ() {
        return LoginRequestDTO.builder().email("jon@dhoe.com").password("test1234").build();
    }

    public static LoginResponseDTO DEFAULT_LOGIN_RESPONSE_OBJ() {
        return LoginResponseDTO.builder().token(DEFAULT_TOKEN).associateDTO(new AssociateDTO()).build();
    }

    public static RegisterRequestDTO DEFAULT_REGISTER_REQUEST_OBJ() {
        return RegisterRequestDTO.builder().associateDTO(AssociateDTO.fromModel(AssociateMocks.DEFAULT_ASSOCIATE())).password("teste123").passwordConfirm("teste123").build();
    }

    public static RegisterRequestDTO DEFAULT_REGISTER_RESPONSE_OBJ() {
        return RegisterRequestDTO.builder().associateDTO(AssociateDTO.fromModel(AssociateMocks.DEFAULT_ASSOCIATE())).password("teste123").passwordConfirm("teste123").build();
    }
}