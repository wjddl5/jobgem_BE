package com.sist.jobgem.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "hire_kind")
public class HireKind {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hk_idx", nullable = false)
    private Integer id;

    @Column(name = "hk_name", length = 30)
    private String hkName;

}