package com.sist.jobgem.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "interviews")
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "in_idx", nullable = false)
    private Integer id;

    @Column(name = "jo_idx", nullable = false)
    private Integer joIdx;

    @Column(name = "co_idx", nullable = false)
    private Integer coIdx;

    @Column(name = "in_content", nullable = false)
    private String inContent;

    @Column(name = "in_write_date", nullable = false)
    private LocalDate inWriteDate;

    @Column(name = "in_state")
    private Integer inState;

}