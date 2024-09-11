package com.sist.jobgem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "blocks")
public class Block {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bl_idx", nullable = false)
    private Integer id;

    @Column(name = "co_idx", nullable = true, insertable = true, updatable = true)
    private Integer coIdx;

    @Column(name = "jo_idx", nullable = true, insertable = true, updatable = true)
    private Integer joIdx;

    @Column(name = "bl_date", nullable = false)
    private LocalDate blDate;

    @Column(name = "bl_content", nullable = false)
    private String blContent;

    @OneToOne
    @JoinColumn(name = "jo_idx", nullable = false, insertable = false, updatable = false)
    private Jobseeker jobseeker;

    @OneToOne
    @JoinColumn(name = "co_idx", nullable = false, insertable = false, updatable = false)
    private Company company;
}