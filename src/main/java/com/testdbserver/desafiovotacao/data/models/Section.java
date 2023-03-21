package com.testdbserver.desafiovotacao.data.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.testdbserver.desafiovotacao.infra.enums.SectionStatusEnum;
import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.*;
import org.hibernate.annotations.Type;

import java.util.Date;
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

    @Column(name="status", nullable = false)
    @Enumerated(EnumType.STRING)
    private SectionStatusEnum status;

    @Column(name="created_at", nullable = false)
    private Date createdAt;

    @FutureOrPresent
    @Column(name="dt_start", nullable = false)
    private Date dtStart;

    @Column(name="duration", nullable = false)
    private int duration;

    @JsonBackReference
    public Pauta getPauta() {
        return pauta;
    }
}
