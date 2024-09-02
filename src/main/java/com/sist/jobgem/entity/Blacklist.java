package com.sist.jobgem.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
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

    @Column(name = "us_idx", nullable = false)
    private Integer usIdx;

    @Column(name = "bl_title", nullable = false, length = 20)
    private String blTitle;

    @Column(name = "bl_content", nullable = false, length = 100)
    private String blContent;

    @Column(name = "bl_date", nullable = false)
    private LocalDate blDate;

    @Column(name = "bl_state", nullable = false)
    private Integer blState;

}