package com.sist.jobgem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "resume_view")
public class ResumeView {
    @Id
    @Column(name = "rv_idx", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "co_idx", nullable = false)
    private Integer coIdx;

    @NotNull
    @Column(name = "ap_idx", nullable = false)
    private Integer apIdx;

    @NotNull
    @Column(name = "po_idx", nullable = false)
    private Integer poIdx;

    @NotNull
    @Column(name = "re_idx", nullable = false)
    private Integer reIdx;

    @NotNull
    @Column(name = "jo_idx", nullable = false)
    private Integer joIdx;

    @NotNull
    @Column(name = "view_status", nullable = false)
    private Integer viewStatus;

}