package com.sist.jobgem.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "applyments")
public class Applyment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ap_idx", nullable = false)
    private Integer id;

    @Column(name = "po_idx", nullable = false)
    private Integer poIdx;

    @Column(name = "jo_idx", nullable = false)
    private Integer joIdx;

    @Column(name = "re_idx", nullable = false)
    private Integer reIdx;

    @Column(name = "ap_date", nullable = false)
    private LocalDate apDate;

    @Column(name = "ap_pass")
    private Integer apPass;

    @Column(name = "ap_read")
    private Integer apRead;

    @Column(name = "ap_state", nullable = false)
    private Integer apState;

    @ManyToOne
    @JoinColumn(name = "po_idx", referencedColumnName = "po_idx", insertable = false, updatable = false)
    private Post post;
}