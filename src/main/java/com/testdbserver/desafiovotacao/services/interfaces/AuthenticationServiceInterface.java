package com.testdbserver.desafiovotacao.services.interfaces;

import com.testdbserver.desafiovotacao.web.DTO.LoginResponseDTO;
import com.testdbserver.desafiovotacao.web.DTO.LoginRequestDTO;
import com.testdbserver.desafiovotacao.web.DTO.RegisterRequestDTO;

public interface AuthenticationServiceInterface {
    public LoginResponseDTO login(LoginRequestDTO loginData);

    public LoginResponseDTO register(RegisterRequestDTO registerData);

}
