package com.testdbserver.desafiovotacao.data.models;

import com.testdbserver.desafiovotacao.infra.enums.AssociateStatusEnum;
import com.testdbserver.desafiovotacao.infra.enums.SectionStatusEnum;
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
    @Enumerated(EnumType.STRING)
    private SectionStatusEnum status;

    @Column(name="created_at")
    private Date createdAt;

    @Column(name="dt_start")
    private Date dtStart;

    @Column(name="duration")
    private int duration;
}
