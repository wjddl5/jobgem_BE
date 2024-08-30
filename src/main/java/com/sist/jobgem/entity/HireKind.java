package com.sist.jobgem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "hire_kind")
public class HireKind {
    @Id
    @Column(name = "hk_idx", nullable = false)
    private Integer id;

    @Size(max = 30)
    @Column(name = "hk_name", length = 30)
    private String hkName;

    @NotNull
    @Column(name = "po_idx", nullable = false)
    private Integer poIdx;

}