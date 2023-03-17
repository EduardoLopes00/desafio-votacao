package com.testdbserver.desafiovotacao.data.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name="section")
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name="pauta_id")
    private Pauta pauta;

    @Column(name="status")
    private int status;

    @Column(name="created_at")
    private Date createdAt;

    @Column(name="duration")
    private int duration;
}
