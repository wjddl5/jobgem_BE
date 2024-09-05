package com.sist.jobgem.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "have_skills")
public class HaveSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hs_idx", nullable = false)
    private Integer id;

    @Column(name = "jo_idx", nullable = false)
    private Integer joIdx;

    @Column(name = "sk_idx", nullable = false)
    private Integer skIdx;

    @ManyToOne
    @JoinColumn(name = "jo_idx", nullable = false, updatable = false, insertable = false)
    private Jobseeker jobseeker;

    @ManyToOne
    @JoinColumn(name = "sk_idx", nullable = false, updatable = false, insertable = false)
    private Skill skill;

}