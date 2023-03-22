package com.testdbserver.desafiovotacao.data.repositories;

import com.testdbserver.desafiovotacao.data.models.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface SectionRepository extends JpaRepository<Section, UUID> {
    @Query(value = " SELECT s FROM Section s " +
            "      WHERE (?1=false and s.status <> 'FINISHED') " +
            "        AND s.dt_start < ?2 " +
            "      ORDER BY s.dt_start asc", nativeQuery = true)
    public List<Section> getAllSections(boolean allowFinishedSections, Date maxDate);
}
