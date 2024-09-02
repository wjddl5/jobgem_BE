package com.sist.jobgem.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "boards")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bo_idx", nullable = false)
    private Integer id;

    @Column(name = "us_idx", nullable = false)
    private Integer usIdx;

    @Column(name = "bo_type", nullable = false)
    private Integer boType;

    @Column(name = "bo_title", nullable = false, length = 50)
    private String boTitle;

    @Column(name = "bo_content", nullable = false, length = 100)
    private String boContent;

    @Column(name = "bo_writedate", nullable = false)
    private LocalDate boWritedate;

    @Column(name = "bo_hit", nullable = false)
    private Integer boHit;

    @Column(name = "bo_like")
    private Integer boLike;

    @Column(name = "bo_answer")
    private Integer boAnswer;

    @Column(name = "bo_image", length = 100)
    private String boImage;

    @Column(name = "bo_status", nullable = false)
    private Integer boStatus;

    @ManyToOne
    @JoinColumn(name = "us_idx")
    private User user;
}