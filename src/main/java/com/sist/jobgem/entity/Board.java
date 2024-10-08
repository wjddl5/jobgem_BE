package com.sist.jobgem.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "bo_content", nullable = false, length = 1000)
    private String boContent;

    @Column(name = "bo_writedate", nullable = false)
    private LocalDate boWritedate;

    @Column(name = "bo_hit", nullable = false)
    private Integer boHit;

    @Column(name = "bo_like")
    private Integer boLike;

    @Column(name = "bo_answer")
    private Integer boAnswer;

    @Column(name = "bo_status", nullable = false)
    private Integer boStatus;

    @ManyToOne
    @JoinColumn(name = "us_idx", insertable = false, updatable = false)
    private User user;

    @OneToMany(mappedBy = "board")
    private List<Comment> commList;
}