package com.testdbserver.desafiovotacao.web.controllers;

import com.testdbserver.desafiovotacao.data.models.Section;
import com.testdbserver.desafiovotacao.services.SectionService;
import com.testdbserver.desafiovotacao.web.DTO.SearchSectionsFiltersDTO;
import com.testdbserver.desafiovotacao.web.DTO.SectionDTO;
import com.testdbserver.desafiovotacao.web.DTO.SectionListDTO;
import com.testdbserver.desafiovotacao.web.controllers.interfaces.SectionControllerInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("section")
public class SectionController implements SectionControllerInterface {
    @Autowired
    SectionService sectionService;

    @Override
    @GetMapping(value = "/{id}")
    public ResponseEntity<Section> getById(@PathVariable UUID id) {
        Section section = sectionService.getSectionById(id);

        return ResponseEntity.ok(section);
    }

    @Override
    @PostMapping()
    public ResponseEntity<Section> createSection(@RequestBody SectionDTO sectionDTO) {
        Section newSection = sectionService.createSection(sectionDTO);

        return ResponseEntity.status(201).body(newSection);
    }

    @Override
    @PostMapping("/search")
    public ResponseEntity<List<SectionListDTO>> searchSections(@RequestBody SearchSectionsFiltersDTO searchSectionsFiltersDTO) {
        List<SectionListDTO> sectionlist = sectionService.searchSections(searchSectionsFiltersDTO);

        return ResponseEntity.status(200).body(sectionlist);
    }
}
