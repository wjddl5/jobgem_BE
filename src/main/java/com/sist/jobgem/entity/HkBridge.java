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
@Table(name = "hk_bridge")
public class HkBridge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hkb_idx", nullable = false)
    private Integer id;

    @Column(name = "po_idx", nullable = false)
    private Integer poIdx;

    @Column(name = "hk_idx", nullable = false)
    private Integer hkIdx;

}