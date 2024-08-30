package com.sist.jobgem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "board")
public class Board {
    @Id
    @Column(name = "bo_idx", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "us_idx", nullable = false)
    private Integer usIdx;

    @NotNull
    @Column(name = "bo_type", nullable = false)
    private Integer boType;

    @Size(max = 50)
    @NotNull
    @Column(name = "bo_title", nullable = false, length = 50)
    private String boTitle;

    @Size(max = 100)
    @NotNull
    @Column(name = "bo_content", nullable = false, length = 100)
    private String boContent;

    @NotNull
    @Column(name = "bo_writedate", nullable = false)
    private LocalDate boWritedate;

    @NotNull
    @Column(name = "bo_hit", nullable = false)
    private Integer boHit;

    @Column(name = "bo_like")
    private Integer boLike;

    @Column(name = "bo_answer")
    private Integer boAnswer;

    @NotNull
    @Column(name = "bo_status", nullable = false)
    private Integer boStatus;

    @OneToOne
    @JoinColumn(name = "us_idx", insertable = false, updatable = false)
    private User user;

    @OneToMany
    @JoinColumn(name = "bo_idx", insertable = false, updatable = false)
    private List<Comment> commentList;
}