package com.sist.jobgem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "have_skills")
public class HaveSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ce_idx", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "j_idx", nullable = false)
    private Integer jIdx;

    @NotNull
    @Column(name = "sk_idx", nullable = false)
    private Integer skIdx;

    @Size(max = 10)
    @NotNull
    @Column(name = "sk_name", nullable = false, length = 10)
    private String skName;

}