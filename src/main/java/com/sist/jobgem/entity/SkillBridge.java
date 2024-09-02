package com.sist.jobgem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "skill_bridge")
public class SkillBridge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sb_bridge", nullable = false)
    private Integer id;

    @Column(name = "sk_idx", nullable = false)
    private Integer skIdx;

    @Column(name = "po_idx", nullable = false)
    private Integer poIdx;

}