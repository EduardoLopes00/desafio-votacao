package com.testdbserver.desafiovotacao.data.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name="pauta")
public class Pauta {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="subject")
    private String subject;

    @Column(name="description")
    private String description;

    @OneToMany(mappedBy = "pauta")
    private List<Section> sections;
}
