package com.sist.jobgem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "blacklist")
public class Blacklist {
    @Id
    @Column(name = "bl_idx", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "co_idx", nullable = false)
    private Integer coIdx;

    @NotNull
    @Column(name = "jo_idx", nullable = false)
    private Integer joIdx;

    @NotNull
    @Column(name = "us_idx", nullable = false)
    private Integer usIdx;

    @Size(max = 20)
    @NotNull
    @Column(name = "bl_title", nullable = false, length = 20)
    private String blTitle;

    @Size(max = 100)
    @NotNull
    @Column(name = "bl_content", nullable = false, length = 100)
    private String blContent;

    @NotNull
    @Column(name = "bl_date", nullable = false)
    private LocalDate blDate;

    @NotNull
    @Column(name = "bl_state", nullable = false)
    private Integer blState;

}