package com.sist.jobgem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "scraps")
public class Scrap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sc_idx", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "po_idx", nullable = false)
    private Integer poIdx;

    @NotNull
    @Column(name = "jo_idx", nullable = false)
    private Integer joIdx;

    @Column(name = "sc_date")
    private LocalDate scDate;

    @NotNull
    @Column(name = "sc_state", nullable = false)
    private Integer scState;

}