package com.sist.jobgem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "interest_companies")
public class InterestCompany {
    @Id
    @Column(name = "ic_idx", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "jo_idx", nullable = false)
    private Integer joIdx;

    @NotNull
    @Column(name = "co_idx", nullable = false)
    private Integer coIdx;

    @NotNull
    @Column(name = "ic_date", nullable = false)
    private LocalDate icDate;

}