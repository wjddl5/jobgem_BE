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
@Table(name = "socials")
public class Social {
    @Id
    @Column(name = "so_idx", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "us_idx", nullable = false)
    private Integer usIdx;

    @Size(max = 100)
    @NotNull
    @Column(name = "so_id", nullable = false, length = 100)
    private String soId;

    @NotNull
    @Column(name = "us_idx2", nullable = false)
    private Integer usIdx2;

}