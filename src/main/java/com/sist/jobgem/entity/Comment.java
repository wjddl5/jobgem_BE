package com.sist.jobgem.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comm_idx", nullable = false)
    private Integer id;


    @Column(name = "bo_idx")
    private Integer boIdx;

    @Column(name = "us_idx")
    private Integer usIdx;

    @Column(name = "comm_content", nullable = false, length = 100)
    private String commContent;

    @Column(name = "comm_status", nullable = false)
    private Integer commStatus;

    @ManyToOne

    @JoinColumn(name = "bo_idx", nullable = false, insertable = false, updatable = false)
    private Board board;

    @ManyToOne
    @JoinColumn(name = "us_idx", nullable = false, insertable = false, updatable = false)

    private User user;
}