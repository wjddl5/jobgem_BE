package com.sist.jobgem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "hk_bridge")
public class HkBridge {
    @Id
    @Column(name = "hkb__idx", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "po_idx", nullable = false)
    private Integer poIdx;

    @NotNull
    @Column(name = "hk_idx", nullable = false)
    private Integer hkIdx;

}