package com.sist.jobgem.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "education_bridge")
public class EducationBridge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "edb_idx", nullable = false)
    private Integer id;

    @Column(name = "ed_idx", nullable = false)
    private Integer edIdx;

    @Column(name = "po_idx", nullable = false)
    private Integer poIdx;

}