package com.sist.jobgem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "have_careers")
public class HaveCareer {
    @Id
    @Column(name = "hc_idx", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "co_idx", nullable = false)
    private Integer coIdx;

    @Size(max = 10)
    @NotNull
    @Column(name = "hc_work", nullable = false, length = 10)
    private String hcWork;

    @NotNull
    @Column(name = "hc_state", nullable = false)
    private Integer hcState;

}