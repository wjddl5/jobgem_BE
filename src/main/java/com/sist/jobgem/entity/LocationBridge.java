package com.sist.jobgem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "location_bridge")
public class LocationBridge {
    @Id
    @Column(name = "lb_idx", nullable = false)
    private Integer id;

    @Column(name = "lg_idx")
    private Integer lgIdx;

    @Column(name = "po_idx")
    private Integer poIdx;

    @Column(name = "ld_idx")
    private Integer ldIdx;

}