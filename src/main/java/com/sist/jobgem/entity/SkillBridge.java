package com.sist.jobgem.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "skill_bridge")
public class SkillBridge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sb_bridge", nullable = false)
    private Integer id;

    @Column(name = "sk_idx", nullable = false, updatable = false, insertable = false)
    private Integer skIdx;

    @Column(name = "po_idx", nullable = false, updatable = false, insertable = false)
    private Integer poIdx;

    @ManyToOne
    @JoinColumn(name = "po_idx", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "sk_idx", nullable = false)
    private Skill skill;

}