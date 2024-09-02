package com.sist.jobgem.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "interest_companies")
public class InterestCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ic_idx", nullable = false)
    private Integer id;

    @Column(name = "jo_idx", nullable = false)
    private Integer joIdx;

    @Column(name = "co_idx", nullable = false)
    private Integer coIdx;

    @Column(name = "ic_date", nullable = false)
    private LocalDate icDate;

}