package com.sist.jobgem.entity;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "logs")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lo_idx", nullable = false)
    private Integer id;

    @Column(name = "us_idx", nullable = false)
    private Integer usIdx;

    @Column(name = "us_type", nullable = false)
    private Integer usType;

    @Column(name = "lo_content", nullable = false, length = 100)
    private String loContent;

    @Column(name = "lo_time", nullable = false)
    private Instant loTime;

}