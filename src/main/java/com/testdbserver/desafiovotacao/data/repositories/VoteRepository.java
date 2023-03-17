package com.testdbserver.desafiovotacao.data.repositories;

import com.testdbserver.desafiovotacao.data.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VoteRepository extends JpaRepository<Vote, UUID> {
}
