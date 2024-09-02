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
@Table(name = "scraps")
public class Scrap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sc_idx", nullable = false)
    private Integer id;

    @Column(name = "po_idx", nullable = false)
    private Integer poIdx;

    @Column(name = "jo_idx", nullable = false)
    private Integer joIdx;

    @Column(name = "sc_date")
    private LocalDate scDate;

    @Column(name = "sc_state", nullable = false)
    private Integer scState;

}