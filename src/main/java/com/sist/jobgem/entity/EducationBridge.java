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
@Table(name = "education_bridge")
public class EducationBridge {
    @Id
    @Column(name = "edb_idx", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "ed_idx", nullable = false)
    private Integer edIdx;

    @NotNull
    @Column(name = "po_idx", nullable = false)
    private Integer poIdx;

}