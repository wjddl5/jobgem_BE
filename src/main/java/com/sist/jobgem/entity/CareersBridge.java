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
@Table(name = "careers_bridge")
public class CareersBridge {
    @Id
    @Column(name = "cb_idx", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "cr_idx", nullable = false)
    private Integer crIdx;

    @NotNull
    @Column(name = "po_idx", nullable = false)
    private Integer poIdx;

}