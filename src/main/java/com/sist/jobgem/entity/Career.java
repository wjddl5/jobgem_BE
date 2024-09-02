package com.sist.jobgem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "career")
public class Career {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cr_idx", nullable = false)
    private Integer id;

    @Column(name = "cr_name", length = 30)
    private String crName;

}