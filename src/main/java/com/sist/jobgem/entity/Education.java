package com.sist.jobgem.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "education")
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ed_idx", nullable = false)
    private Integer id;

    @Column(name = "ed_name", length = 30)
    private String edName;

}