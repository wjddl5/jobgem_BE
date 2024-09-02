package com.sist.jobgem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "hire_kind")
public class HireKind {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hk_idx", nullable = false)
    private Integer id;

    @Size(max = 30)
    @Column(name = "hk_name", length = 30)
    private String hkName;

}