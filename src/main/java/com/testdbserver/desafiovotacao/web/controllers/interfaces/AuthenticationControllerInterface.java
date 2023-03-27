package com.testdbserver.desafiovotacao.web.controllers.interfaces;

import com.testdbserver.desafiovotacao.web.DTO.AuthResponseDTO;
import com.testdbserver.desafiovotacao.web.DTO.LoginRequestDTO;
import com.testdbserver.desafiovotacao.web.DTO.RegisterRequestDTO;
import org.springframework.http.ResponseEntity;

public interface AuthenticationControllerInterface {
    public ResponseEntity<AuthResponseDTO> login(LoginRequestDTO loginData);

    public ResponseEntity<AuthResponseDTO> register(RegisterRequestDTO registerData);
}
