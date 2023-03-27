package com.testdbserver.desafiovotacao.services.interfaces;

import com.testdbserver.desafiovotacao.web.DTO.AuthResponseDTO;
import com.testdbserver.desafiovotacao.web.DTO.LoginRequestDTO;
import com.testdbserver.desafiovotacao.web.DTO.RegisterRequestDTO;

public interface AuthenticationServiceInterface {
    public AuthResponseDTO login(LoginRequestDTO loginData);

    public AuthResponseDTO register(RegisterRequestDTO registerData);

}
