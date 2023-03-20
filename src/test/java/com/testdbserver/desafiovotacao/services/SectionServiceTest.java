package com.testdbserver.desafiovotacao.services;

import com.testdbserver.desafiovotacao.data.models.Section;
import com.testdbserver.desafiovotacao.data.repositories.PautaRepository;
import com.testdbserver.desafiovotacao.data.repositories.SectionRepository;
import com.testdbserver.desafiovotacao.infra.exceptions.InvalidDataException;
import com.testdbserver.desafiovotacao.infra.exceptions.NotFoundException;
import com.testdbserver.desafiovotacao.utils.mocks.PautaMocks;
import com.testdbserver.desafiovotacao.utils.mocks.SectionMocks;
import com.testdbserver.desafiovotacao.web.DTO.SectionDTO;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SectionServiceTest {

    @InjectMocks
    SectionService sectionService;

    @Mock
    private SectionRepository sectionRepository;

    @Mock
    private PautaRepository pautaRepository;

    @Test
    public void shouldReturnSection_WhenGetSectionByIdIsCalledWithExistingId() {
       when(sectionRepository.findById(SectionMocks.SECTION_ID_1)).thenReturn(Optional.of(SectionMocks.SECTION_1()));

       Section sectionUT = sectionService.getSectionById(SectionMocks.SECTION_ID_1);

       assertNotNull(sectionUT);
    }

    @Test
    public void shouldReturnNotFoundException_WhenGetSectionByIdIsCalledWithExistingId() {
        UUID nonExistentSectionId = UUID.fromString("e12c92e6-c617-464b-9cd1-9c8fbd76a6b6");

        when(sectionRepository.findById(nonExistentSectionId)).thenThrow(new NotFoundException(nonExistentSectionId.toString()));

        assertThrows(NotFoundException.class, () -> sectionService.getSectionById(nonExistentSectionId));
    }

    @Test
    public void shouldCreateSection_WhenCreateSectionWithValidData() throws Exception {
        Section testingSection = SectionMocks.SECTION_1();

        when(sectionRepository.saveAndFlush(any(Section.class))).thenReturn(testingSection);

        Section sectionUT = sectionService.createSection(SectionDTO.fromModel(testingSection));

        assertNotNull(sectionUT);
        assertEquals(testingSection.getDtStart(), sectionUT.getDtStart());
    }

    @Test
    public void shouldThrowInternalServerError_WhenCreateSectionWithInvalidDtStart() throws Exception {
        Section testingSection = SectionMocks.SECTION_1();
        testingSection.setDtStart(DateUtils.addHours(new Date(), -3));

        when(pautaRepository.findById(any(UUID.class))).thenReturn(Optional.of(PautaMocks.DEFAULT_PAUTA()));
        when(sectionRepository.saveAndFlush(any(Section.class))).thenThrow(new InvalidDataException(testingSection.getDtStart().toString(), "dtStart", "It must be a future date"));

        assertThrows(InvalidDataException.class, () -> sectionService.createSection(SectionDTO.fromModel(testingSection)), "You inserted an invalid value("+ testingSection.getDtStart().toString() +") for the field dtStart. The reason is: It must be a future date");
    }

    @Test
    public void shouldThrowNotFoundException_WhenCreateSectionWithNonexistentPautaId() throws Exception {
        UUID nonExistentPautaId = UUID.fromString("bd0b648a-5c10-4ba4-93e4-f8ec93e24632");
        SectionDTO testingSectionDTO = SectionDTO.fromModel(SectionMocks.SECTION_1());
        testingSectionDTO.setPautaId(nonExistentPautaId);

        when(pautaRepository.findById(nonExistentPautaId)).thenThrow(new InvalidDataException(nonExistentPautaId.toString(), "pautaId", "It doesn't have a Pauta associate with this ID"));

        assertThrows(InvalidDataException.class, () -> sectionService.createSection(testingSectionDTO), "You inserted an invalid value("+ testingSectionDTO.getPautaId() +") for the field pautaId. The reason is: It doesn't have a Pauta associate with this ID");
    }
}
