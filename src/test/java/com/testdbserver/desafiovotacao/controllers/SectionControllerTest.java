package com.testdbserver.desafiovotacao.controllers;

import com.testdbserver.desafiovotacao.data.models.Pauta;
import com.testdbserver.desafiovotacao.data.models.Section;
import com.testdbserver.desafiovotacao.infra.exceptions.InvalidDataException;
import com.testdbserver.desafiovotacao.infra.exceptions.NotFoundException;
import com.testdbserver.desafiovotacao.services.SectionService;
import com.testdbserver.desafiovotacao.utils.TestUtilsFunctions;
import com.testdbserver.desafiovotacao.utils.mocks.PautaMocks;
import com.testdbserver.desafiovotacao.utils.mocks.SectionMocks;
import com.testdbserver.desafiovotacao.web.DTO.PautaDTO;
import com.testdbserver.desafiovotacao.web.DTO.SectionDTO;
import com.testdbserver.desafiovotacao.web.controllers.SectionController;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Date;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SectionController.class)
public class SectionControllerTest {
    private final String basePath = "/section";

    @MockBean
    private SectionService sectionService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturn200_WhenRequestGetSectionByIdIsCalledWithExistingId() throws Exception {
        when(sectionService.getSectionById(SectionMocks.SECTION_ID_1)).thenReturn(SectionMocks.SECTION_1());

        mockMvc.perform(get(basePath + "/{id}", SectionMocks.SECTION_ID_1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("WAITING_TO_START"));
    }

    @Test
    public void shouldReturn404_WhenRequestGetSectionByIdIsCalledWithNonexistentId() throws Exception {
        UUID nonExistentSectionid = UUID.fromString("78709680-0978-4bb5-8150-d40050101a47");

        when(sectionService.getSectionById(nonExistentSectionid)).thenThrow(new NotFoundException(nonExistentSectionid.toString()));

        mockMvc.perform(get(basePath + "/{id}", nonExistentSectionid))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Item not found for ID " + nonExistentSectionid.toString()));


    }

    @Test
    public void shouldReturn201_WhenRequestCreateSectionWithValidData() throws Exception {
        Section testingSection = SectionMocks.SECTION_1();

        when (sectionService.createSection(any (SectionDTO.class))).thenReturn(testingSection);

        mockMvc.perform(post(basePath)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtilsFunctions.convertObjectToJSON(SectionDTO.fromModel(testingSection)))).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.pauta.subject").value(testingSection.getPauta().getSubject()))
                .andExpect(jsonPath("$.duration").value(testingSection.getDuration()));
    }

    @Test
    public void shouldReturn500_WhenCreateSectionWithInvalidDtStart() throws Exception {
        Section testingSection = SectionMocks.SECTION_1();
        testingSection.setDtStart(DateUtils.addHours(new Date(), -3));

        System.out.println("LALALALA: " + testingSection.getDtStart());

        when(sectionService.createSection(SectionDTO.fromModel(testingSection))).thenThrow(new InvalidDataException(testingSection.getDtStart().toString(), "dtStart", "It must be a future date"));

        mockMvc.perform(post(basePath)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtilsFunctions.convertObjectToJSON(SectionDTO.fromModel(testingSection)))).andDo(MockMvcResultHandlers.print())
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").value("You inserted an invalid value("+ testingSection.getDtStart().toString() +") for the field dtStart. The reason is: It must be a future date"));
    }
}
