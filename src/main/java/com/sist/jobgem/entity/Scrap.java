package com.sist.jobgem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "scrap")
public class Scrap {
    @Id
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