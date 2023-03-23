package com.testdbserver.desafiovotacao.data.models;

import com.testdbserver.desafiovotacao.infra.enums.VoteOptionEnum;
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

    @Column(name = "associate_id")
    private UUID associateId;

    @Column(name = "section_id")
    private UUID sectionId;

    @OneToOne
    @JoinColumn(name = "associate_id", insertable = false, updatable = false)
    private Associate associate;

    @OneToOne
    @JoinColumn(name = "section_id", insertable = false, updatable = false)
    private Section section;

    @Column(name="voted_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date votedAt;

    @Column(name="vote_option")
    @Enumerated(EnumType.STRING)
    private VoteOptionEnum voteOption;
}
