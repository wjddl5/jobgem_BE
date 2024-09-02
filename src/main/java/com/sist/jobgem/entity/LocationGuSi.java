package com.sist.jobgem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "location_gu_si")
public class LocationGuSi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lg_idx", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "ld_idx", nullable = false)
    private Integer ldIdx;

    @Size(max = 10)
    @NotNull
    @Column(name = "lg_name", nullable = false, length = 10)
    private String lgName;

}