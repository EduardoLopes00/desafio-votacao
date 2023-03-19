package com.testdbserver.desafiovotacao.services;

import com.testdbserver.desafiovotacao.data.models.Associate;
import com.testdbserver.desafiovotacao.data.models.Pauta;
import com.testdbserver.desafiovotacao.data.repositories.PautaRepository;
import com.testdbserver.desafiovotacao.infra.exceptions.NotFoundException;
import com.testdbserver.desafiovotacao.utils.mocks.AssociateMocks;
import com.testdbserver.desafiovotacao.utils.mocks.PautaMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PautaServiceTest {

    @InjectMocks
    PautaService pautaService;

    @Mock
    private PautaRepository pautaRepository;

    @Test
    public void shouldReturnPauta_WhenGetPautaByIdIsCalledWithExistingId() {
        when(pautaRepository.findById(PautaMocks.DEFAULT_PAUTA_ID)).thenReturn(Optional.of(PautaMocks.DEFAULT_PAUTA()));

        Pauta pautaUT = pautaService.getPautaById(PautaMocks.DEFAULT_PAUTA_ID);

        assertNotNull(pautaUT);
    }

    @Test
    public void shouldThrowNotFoundException_WhenGetAssociateByIdThatNotExists() {
        UUID nonexistentPautaId = UUID.fromString("1691792f-af2f-48f3-a967-8caa1eee93d3");

        when(pautaRepository.findById(nonexistentPautaId)).thenThrow(new NotFoundException(nonexistentPautaId.toString()));

        assertThrows(NotFoundException.class, () -> pautaService.getPautaById(nonexistentPautaId));
    }
}

