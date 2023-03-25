package com.testdbserver.desafiovotacao.data.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.testdbserver.desafiovotacao.data.enums.SectionStatusEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.*;
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
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @FutureOrPresent
    @Column(name="dt_start", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtStart;

    @Column(name="duration", nullable = false)
    private int duration;

    @JsonBackReference
    public Pauta getPauta() {
        return pauta;
    }
}
