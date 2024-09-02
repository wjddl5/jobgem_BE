package com.sist.jobgem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "skill_bridge")
public class SkillBridge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sb_bridge", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "sk_idx", nullable = false)
    private Integer skIdx;

    @NotNull
    @Column(name = "po_idx", nullable = false)
    private Integer poIdx;

}