package com.testdbserver.desafiovotacao.data.repositories;

import com.testdbserver.desafiovotacao.data.models.Associate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AssociateRepository extends JpaRepository<Associate, UUID> {

    public Associate findFirstByCpf(String cpf);

    public Optional<Associate> findFirstByEmail(String email);
}
