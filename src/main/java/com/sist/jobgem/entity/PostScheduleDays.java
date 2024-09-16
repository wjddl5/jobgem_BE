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
    @Column(name = "psd_idx")
    private Integer id;

    @Column(name = "po_idx")
    private Integer poIdx;

    @Column(name = "wd_idx")
    private Integer wdIdx;
}



