package com.sist.jobgem.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "blocks")
public class Block {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bl_idx", nullable = false)
    private Integer id;

    @Column(name = "co_idx", nullable = false , insertable=false, updatable=false)
    private Integer coIdx;

    @Column(name = "jo_idx", nullable = false, insertable=false, updatable=false)
    private Integer joIdx;

    @Column(name = "bl_date", nullable = false)
    private LocalDate blDate;

    @Column(name = "bl_content", nullable = false)
    private String blContent;

    @OneToOne
    @JoinColumn(name = "jo_idx")
    private Jobseeker jobseeker;
}