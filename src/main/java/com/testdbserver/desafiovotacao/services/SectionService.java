package com.testdbserver.desafiovotacao.services;

import com.testdbserver.desafiovotacao.data.models.Pauta;
import com.testdbserver.desafiovotacao.data.models.Section;
import com.testdbserver.desafiovotacao.data.repositories.PautaRepository;
import com.testdbserver.desafiovotacao.data.repositories.SectionRepository;
import com.testdbserver.desafiovotacao.infra.exceptions.InvalidDataException;
import com.testdbserver.desafiovotacao.infra.exceptions.NotFoundException;
import com.testdbserver.desafiovotacao.services.interfaces.SectionServiceInterface;
import com.testdbserver.desafiovotacao.web.DTO.SearchSectionsFiltersDTO;
import com.testdbserver.desafiovotacao.web.DTO.SectionDTO;
import com.testdbserver.desafiovotacao.web.DTO.SectionListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class SectionService implements SectionServiceInterface {

    @Autowired
    SectionRepository sectionRepository;

    @Autowired
    PautaRepository pautaRepository;


    @Override
    public List<SectionListDTO> searchSections(SearchSectionsFiltersDTO searchSectionsFiltersDTO) {
        List<SectionListDTO> sectionlistDto = new ArrayList<>();

        List<Section> sectionList = sectionRepository.getAllSections(searchSectionsFiltersDTO.isAllowFinishedSections(), searchSectionsFiltersDTO.getMaxDate());

        if (!sectionList.isEmpty()) {
            sectionlistDto = sectionList.stream().map((section) -> SectionListDTO.fromModel(section)).toList();
        }

        return sectionlistDto;
    }

    @Override
    public Section getSectionById(UUID id) {
        return sectionRepository.findById(id).orElseThrow(() -> new NotFoundException(id.toString())) ;
    }

    @Override
    public Section createSection(SectionDTO sectionDTO) {
        Pauta pauta = pautaRepository.findById(sectionDTO.getPautaId()).orElseThrow(() -> new InvalidDataException(sectionDTO.getPautaId().toString(), "pautaId", "It doesn't have a Pauta associate with this ID"));

        if (sectionDTO.getDtStart().getTime() < new Date().getTime()) {
            throw new InvalidDataException(sectionDTO.getDtStart().toString(), "dtStart", "It must be a future date");
        }

        Section newSection = sectionDTO.toModel(pauta);

        newSection.setCreatedAt(new Date());
        return sectionRepository.saveAndFlush(newSection);
    }
}
