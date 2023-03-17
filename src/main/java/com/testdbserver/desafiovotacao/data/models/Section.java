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
@Table(name="section")
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="subject")
    private String subject;

    @Column(name="description")
    private String description;

    @Column(name="status")
    private int status;

    @Column(name="created_at")
    private Date createdAt;

    @Column(name="duration")
    private int duration;
}
