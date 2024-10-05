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
@Table(name = "interest_companies")
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "co_idx", referencedColumnName = "co_idx", insertable = false, updatable = false)
    private Company company;

}