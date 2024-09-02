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
@Table(name = "logs")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lo_idx", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "us_idx", nullable = false)
    private Integer usIdx;

    @NotNull
    @Column(name = "us_type", nullable = false)
    private Integer usType;

    @Size(max = 100)
    @NotNull
    @Column(name = "lo_content", nullable = false, length = 100)
    private String loContent;

    @NotNull
    @Column(name = "lo_time", nullable = false)
    private Instant loTime;

}