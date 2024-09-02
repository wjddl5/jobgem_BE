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