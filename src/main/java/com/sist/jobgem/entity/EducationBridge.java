package com.sist.jobgem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "education_bridge")
public class EducationBridge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "edb_idx", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "ed_idx", nullable = false)
    private Integer edIdx;

    @NotNull
    @Column(name = "po_idx", nullable = false)
    private Integer poIdx;

}