package com.testdbserver.desafiovotacao.web.controllers.interfaces;

import com.testdbserver.desafiovotacao.web.DTO.LoginResponseDTO;
import com.testdbserver.desafiovotacao.web.DTO.LoginRequestDTO;
import com.testdbserver.desafiovotacao.web.DTO.RegisterRequestDTO;
import org.springframework.http.ResponseEntity;

public interface AuthenticationControllerInterface {
    public ResponseEntity<LoginResponseDTO> login(LoginRequestDTO loginData);

    public ResponseEntity<LoginResponseDTO> register(RegisterRequestDTO registerData);
}
