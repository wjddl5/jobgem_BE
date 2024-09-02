package com.sist.jobgem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "applyments")
public class Applyment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ap_idx", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "po_idx", nullable = false)
    private Integer poIdx;

    @NotNull
    @Column(name = "jo_idx", nullable = false)
    private Integer joIdx;

    @NotNull
    @Column(name = "re_idx", nullable = false)
    private Integer reIdx;

    @NotNull
    @Column(name = "ap_date", nullable = false)
    private LocalDate apDate;

    @Column(name = "ap_pass")
    private Integer apPass;

    @Column(name = "ap_read")
    private Integer apRead;

    @NotNull
    @Column(name = "ap_state", nullable = false)
    private Integer apState;

}