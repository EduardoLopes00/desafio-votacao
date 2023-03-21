package com.testdbserver.desafiovotacao.services;

import com.testdbserver.desafiovotacao.data.models.Pauta;
import com.testdbserver.desafiovotacao.data.models.Section;
import com.testdbserver.desafiovotacao.data.repositories.PautaRepository;
import com.testdbserver.desafiovotacao.data.repositories.SectionRepository;
import com.testdbserver.desafiovotacao.infra.enums.SectionStatusEnum;
import com.testdbserver.desafiovotacao.infra.exceptions.InvalidDataException;
import com.testdbserver.desafiovotacao.infra.exceptions.NotFoundException;
import com.testdbserver.desafiovotacao.services.interfaces.SectionServiceInterface;
import com.testdbserver.desafiovotacao.web.DTO.SectionDTO;
import com.testdbserver.desafiovotacao.web.DTO.SectionListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<SectionListDTO> searchSections() {
        return null;
    }

    @Override
    public Section getSectionById(UUID id) {
        return sectionRepository.findById(id).orElseThrow(() -> new NotFoundException(id.toString())) ;
    }

    @Override
    public Section createSection(SectionDTO sectionDTO) {
        Pauta pauta = pautaRepository.findById(sectionDTO.getPautaId()).orElseThrow(() -> new InvalidDataException(sectionDTO.getPautaId().toString(), "pautaId", "It doesn't have a Pauta associate with this ID"));

        Section newSection = sectionDTO.toModel(pauta);

        newSection.setCreatedAt(new Date());
        return sectionRepository.saveAndFlush(newSection);
    }
}
