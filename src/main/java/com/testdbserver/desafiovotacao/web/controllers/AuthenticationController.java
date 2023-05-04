package com.testdbserver.desafiovotacao.web.controllers;

import com.testdbserver.desafiovotacao.services.AuthenticationService;
import com.testdbserver.desafiovotacao.web.DTO.LoginResponseDTO;
import com.testdbserver.desafiovotacao.web.DTO.LoginRequestDTO;
import com.testdbserver.desafiovotacao.web.DTO.RegisterRequestDTO;
import com.testdbserver.desafiovotacao.web.controllers.interfaces.AuthenticationControllerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController implements AuthenticationControllerInterface {
    @Autowired
    AuthenticationService authenticationService;

    @Override
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginData) {
        LoginResponseDTO authResponse = authenticationService.login(loginData);

        return ResponseEntity.ok(authResponse);
    }

    @Override
    @PostMapping("/register")
    public ResponseEntity<LoginResponseDTO> register(@RequestBody RegisterRequestDTO registerData) {
        LoginResponseDTO authResponse = authenticationService.register(registerData);

        return ResponseEntity.status(201).body(authResponse);
    }
}
