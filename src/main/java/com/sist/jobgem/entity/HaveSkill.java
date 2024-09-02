package com.sist.jobgem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "have_skills")
public class HaveSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ce_idx", nullable = false)
    private Integer id;

    @Column(name = "j_idx", nullable = false)
    private Integer jIdx;

    @Column(name = "sk_idx", nullable = false)
    private Integer skIdx;

    @Column(name = "sk_name", nullable = false, length = 10)
    private String skName;

}