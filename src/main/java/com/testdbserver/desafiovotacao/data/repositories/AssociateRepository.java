package com.testdbserver.desafiovotacao.data.repositories;

import com.testdbserver.desafiovotacao.data.models.Associate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AssociateRepository extends JpaRepository<Associate, UUID> {
}
