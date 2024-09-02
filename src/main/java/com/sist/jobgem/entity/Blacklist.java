package com.sist.jobgem.entity;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bl_idx", nullable = false)
    private Integer id;

    @Column(name = "co_idx")
    private Integer coIdx;

    @Column(name = "jo_idx")
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