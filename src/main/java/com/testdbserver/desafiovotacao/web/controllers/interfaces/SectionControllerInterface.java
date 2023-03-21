package com.testdbserver.desafiovotacao.web.controllers.interfaces;

import com.testdbserver.desafiovotacao.data.models.Pauta;
import com.testdbserver.desafiovotacao.data.models.Section;
import com.testdbserver.desafiovotacao.web.DTO.PautaDTO;
import com.testdbserver.desafiovotacao.web.DTO.SectionDTO;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface SectionControllerInterface {
    public ResponseEntity<Section> getById(UUID id);

    public ResponseEntity<Section> createSection(SectionDTO sectionDTO);

}
