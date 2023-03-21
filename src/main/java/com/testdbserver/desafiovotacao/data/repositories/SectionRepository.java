package com.testdbserver.desafiovotacao.data.repositories;

import com.testdbserver.desafiovotacao.data.models.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface SectionRepository extends JpaRepository<Section, UUID>, JpaSpecificationExecutor<Section> {

}
