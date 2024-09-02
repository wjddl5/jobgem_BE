package com.sist.jobgem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "careers_bridge")
public class CareersBridge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cb_idx", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "cr_idx", nullable = false)
    private Integer crIdx;

    @NotNull
    @Column(name = "po_idx", nullable = false)
    private Integer poIdx;

}