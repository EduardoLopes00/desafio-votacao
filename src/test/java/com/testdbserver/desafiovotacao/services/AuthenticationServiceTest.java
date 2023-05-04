package com.testdbserver.desafiovotacao.services;

import com.testdbserver.desafiovotacao.data.models.Associate;
import com.testdbserver.desafiovotacao.data.repositories.AssociateRepository;
import com.testdbserver.desafiovotacao.infra.exceptions.InvalidDataException;
import com.testdbserver.desafiovotacao.infra.jwt.JwtService;
import com.testdbserver.desafiovotacao.utils.TestConfig;
import com.testdbserver.desafiovotacao.utils.mocks.AssociateMocks;
import com.testdbserver.desafiovotacao.utils.mocks.AuthenticationMocks;
import com.testdbserver.desafiovotacao.web.DTO.AssociateDTO;
import com.testdbserver.desafiovotacao.web.DTO.LoginRequestDTO;
import com.testdbserver.desafiovotacao.web.DTO.LoginResponseDTO;
import com.testdbserver.desafiovotacao.web.DTO.RegisterRequestDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Import({TestConfig.class})
@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {
    @InjectMocks
    private AuthenticationService authenticationService;

    @Mock
    private AssociateService associateService;

    @Mock
    private AssociateRepository associateRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Test
    public void shouldLoginCorrectly_WhenTryToLoginWithCorrectCredentials() throws Exception {
        LoginRequestDTO loginRequestDataUt = AuthenticationMocks.DEFAULT_LOGIN_REQUEST_OBJ();

        when(associateRepository.findFirstByEmail(any(String.class))).thenReturn(Optional.ofNullable(AssociateMocks.DEFAULT_ASSOCIATE()));
        when(jwtService.generateToken(any(Associate.class))).thenReturn("fakeToken");
        LoginResponseDTO loginResponseut = authenticationService.login(loginRequestDataUt);

        assertNotNull(loginResponseut);
        assertNotNull(loginResponseut.getToken());
        assertNotNull(loginResponseut.getAssociateDTO());
        verify(authenticationManager, times(1)).authenticate(new UsernamePasswordAuthenticationToken(loginRequestDataUt.getEmail(), loginRequestDataUt.getPassword()));
    }

    @Test
    public void shouldRegisterAssociate_WhenRegisterAssociateWithValidData() throws Exception {
        RegisterRequestDTO testingAssociate = AuthenticationMocks.DEFAULT_REGISTER_REQUEST_OBJ();

        when(associateService.createAssociate(any(AssociateDTO.class), any())).thenReturn(testingAssociate.getAssociateDTO().toModel());
        when(jwtService.generateToken(any(Associate.class))).thenReturn("fakeToken");
        LoginResponseDTO associateUT = authenticationService.register(testingAssociate);

        assertNotNull(associateUT);
        assertNotNull(associateUT.getToken());
        assertEquals(testingAssociate.getAssociateDTO().getCpf(), associateUT.getAssociateDTO().getCpf());
    }

    @Test
    public void shouldThrowInvalidDataException_WhenRegisterAssociateWithDifferentPasswords() throws Exception {
        RegisterRequestDTO testingAssociate = AuthenticationMocks.DEFAULT_REGISTER_REQUEST_OBJ();
        testingAssociate.setPasswordConfirm(testingAssociate.getPassword() + "EXTRADATATOMAKEITDIFFERENT");

        assertThrows(InvalidDataException.class, () -> authenticationService.register(testingAssociate), "Password doesn't match");
    }
}
