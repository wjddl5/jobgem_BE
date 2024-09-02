package com.sist.jobgem.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "careers_bridge")
public class CareersBridge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cb_idx", nullable = false)
    private Integer id;

    @Column(name = "cr_idx", nullable = false)
    private Integer crIdx;
  
    @Column(name = "po_idx", nullable = false)
    private Integer poIdx;

}