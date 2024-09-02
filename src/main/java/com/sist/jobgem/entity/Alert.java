package com.sist.jobgem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "alerts")
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "al_idx", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "us_idx", nullable = false)
    private Integer usIdx;

    @Size(max = 100)
    @NotNull
    @Column(name = "al_content", nullable = false, length = 100)
    private String alContent;

    @NotNull
    @Column(name = "al_date", nullable = false)
    private Instant alDate;

    @NotNull
    @Column(name = "al_state", nullable = false)
    private Integer alState;

}