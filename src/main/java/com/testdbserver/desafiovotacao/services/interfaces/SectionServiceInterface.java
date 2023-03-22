package com.testdbserver.desafiovotacao.services.interfaces;

import com.testdbserver.desafiovotacao.data.models.Section;
import com.testdbserver.desafiovotacao.web.DTO.SearchSectionsFiltersDTO;
import com.testdbserver.desafiovotacao.web.DTO.SectionDTO;
import com.testdbserver.desafiovotacao.web.DTO.SectionListDTO;

import java.util.List;
import java.util.UUID;

public interface SectionServiceInterface {
    public List<SectionListDTO> searchSections(SearchSectionsFiltersDTO searchSectionsFiltersDTO);

    public Section getSectionById(UUID id);

    public Section createSection(SectionDTO sectionDTO);
}
