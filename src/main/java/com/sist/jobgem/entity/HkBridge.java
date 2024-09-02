package com.sist.jobgem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "hk_bridge")
public class HkBridge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hkb__idx", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "po_idx", nullable = false)
    private Integer poIdx;

    @NotNull
    @Column(name = "hk_idx", nullable = false)
    private Integer hkIdx;

}