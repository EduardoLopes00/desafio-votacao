package com.testdbserver.desafiovotacao.data.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name="vote")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    @JoinColumn(name = "associate_id", nullable = false)
    private Associate associate;

    @Column
    @JoinColumn(name = "section_id", nullable = false)
    private Section section;

    @Column(name="voted_at")
    private Date votedAt;


}
