package com.sist.jobgem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "post_schedule_days")
@Getter
@Setter
public class PostScheduleDays {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "psd_idx", nullable = false)
    private Integer id;

    @Column(name = "po_idx", nullable = false)
    private Integer poIdx;

    @Column(name = "wd_idx", nullable = false)
    private Integer wdIdx;
}



