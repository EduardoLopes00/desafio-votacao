package com.testdbserver.desafiovotacao.services;

import com.testdbserver.desafiovotacao.data.models.Associate;
import com.testdbserver.desafiovotacao.data.repositories.AssociateRepository;
import com.testdbserver.desafiovotacao.infra.exceptions.AlreadyExistsException;
import com.testdbserver.desafiovotacao.infra.exceptions.NotFoundException;
import com.testdbserver.desafiovotacao.utils.mocks.AssociateMocks;
import com.testdbserver.desafiovotacao.web.DTO.AssociateDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AssociateServiceTest {

    @InjectMocks
    private AssociateService associateService;

    @Mock
    private AssociateRepository associateRepository;

    @Test
    public void shouldReturnAssociate_WhenGetAssociateByIdIsCalledWithExistingId() throws Exception {
        when(associateRepository.findById(any(UUID.class))).thenReturn(Optional.ofNullable(AssociateMocks.DEFAULT_ASSOCIATE()));

        Associate associateUT = associateService.getAssociateById(AssociateMocks.DEFAULT_ASSOCIATE_ID);

        assertNotNull(associateUT);
    }

    @Test
    public void shouldThrowNotFoundException_WhenGetAssociateByIdThatNotExists() throws Exception {
        UUID nonexistentAssociateId = UUID.fromString("e12c92e6-c617-464b-9cd1-9c8fbd76a6b6");

        when(associateRepository.findById(nonexistentAssociateId)).thenThrow(new NotFoundException(nonexistentAssociateId.toString()));

        assertThrows(NotFoundException.class, () -> associateService.getAssociateById(nonexistentAssociateId));
    }

    @Test
    public void shouldThrowInternalServerErrorException_WhenCreateAssociateWithExistingCPF() throws Exception {
        AssociateDTO testingAssociateDto = AssociateDTO.fromModel(AssociateMocks.DEFAULT_ASSOCIATE());

        when(associateService.isValidAssociate(testingAssociateDto)).thenThrow(new AlreadyExistsException(testingAssociateDto.getCpf(), ""));

        assertThrows(AlreadyExistsException.class, () -> associateService.createAssociate(testingAssociateDto), "The system couldn't complete the action because the item already exists for data " + testingAssociateDto.getCpf());
    }

    @Test
    public void shouldThrowInternalServerErrorException_WhenCreateAssociateWithExistingEmail() throws Exception {
        AssociateDTO testingAssociateDto = AssociateDTO.fromModel(AssociateMocks.DEFAULT_ASSOCIATE());

        when(associateService.isValidAssociate(testingAssociateDto)).thenThrow(new AlreadyExistsException(testingAssociateDto.getEmail(), ""));

        assertThrows(AlreadyExistsException.class, () -> associateService.createAssociate(testingAssociateDto), "The system couldn't complete the action because the item already exists for data " + testingAssociateDto.getEmail());
    }
}
