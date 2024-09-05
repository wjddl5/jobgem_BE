package com.sist.jobgem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "hire_kind")
public class HireKind {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hk_idx", nullable = false)
    private Integer id;

    @Column(name = "hk_name", length = 30)
    private String hkName;

}