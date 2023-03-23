package com.testdbserver.desafiovotacao.web.controllers.interfaces;

import com.testdbserver.desafiovotacao.data.models.Section;
import com.testdbserver.desafiovotacao.web.DTO.SearchSectionsFiltersDTO;
import com.testdbserver.desafiovotacao.web.DTO.SectionDTO;
import com.testdbserver.desafiovotacao.web.DTO.SectionListDTO;
import com.testdbserver.desafiovotacao.web.DTO.VoteBySectionDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface SectionControllerInterface {
    public ResponseEntity<Section> getById(UUID id);

    public ResponseEntity<Section> createSection(SectionDTO sectionDTO);

    public ResponseEntity<List<SectionListDTO>> searchSections(SearchSectionsFiltersDTO searchSectionsFiltersDTO);

    public ResponseEntity<List<VoteBySectionDTO>> getVotesBySection(UUID sectionId);
}
