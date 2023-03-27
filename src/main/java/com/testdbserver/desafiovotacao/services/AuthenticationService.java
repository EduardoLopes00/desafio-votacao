package com.testdbserver.desafiovotacao.services;

import com.testdbserver.desafiovotacao.data.models.Associate;
import com.testdbserver.desafiovotacao.data.repositories.AssociateRepository;
import com.testdbserver.desafiovotacao.infra.exceptions.InvalidDataException;
import com.testdbserver.desafiovotacao.infra.exceptions.NotFoundException;
import com.testdbserver.desafiovotacao.infra.jwt.JwtService;
import com.testdbserver.desafiovotacao.services.interfaces.AuthenticationServiceInterface;
import com.testdbserver.desafiovotacao.web.DTO.AssociateDTO;
import com.testdbserver.desafiovotacao.web.DTO.AuthResponseDTO;
import com.testdbserver.desafiovotacao.web.DTO.LoginRequestDTO;
import com.testdbserver.desafiovotacao.web.DTO.RegisterRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements AuthenticationServiceInterface {
    @Autowired
    AssociateRepository associateRepository;

    @Autowired
    JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AssociateService associateService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public AuthResponseDTO login(LoginRequestDTO loginData) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginData.getEmail(),
                        loginData.getPassword()
                )
        );

        Associate associate = associateRepository.findFirstByEmail(loginData.getEmail())
                .orElseThrow(() -> new NotFoundException(loginData.getEmail()));

        String jwtToken = jwtService.generateToken(associate);
        return AuthResponseDTO.builder().associateDTO(AssociateDTO.fromModel(associate)).token(jwtToken).build();
    }

    @Override
    public AuthResponseDTO register(RegisterRequestDTO registerData) {
        if (!registerData.getPassword().equals(registerData.getPasswordConfirm())){
            throw new InvalidDataException("Passwords doesn't match");
        }

        Associate newAssociate = associateService.createAssociate(registerData.getAssociateDTO(), passwordEncoder.encode(registerData.getPassword()));

        String jwtToken = jwtService.generateToken(newAssociate);
        return AuthResponseDTO
                .builder()
                .associateDTO(AssociateDTO.fromModel(newAssociate))
                .token(jwtToken)
                .build();
    }
}
