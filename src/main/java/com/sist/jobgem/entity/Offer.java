package com.sist.jobgem.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "offers")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "of_idx", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "co_idx")
    private Company company;

    @Column(name = "jo_idx", nullable = false)
    private Integer joIdx;

    @Column(name = "of_content", length = 500)
    private String ofContent;

    @Column(name = "of_type")
    private Integer ofType;

    @Column(name = "of_date")
    private LocalDate ofDate;

    @Column(name = "of_state")
    private Integer ofState;

}