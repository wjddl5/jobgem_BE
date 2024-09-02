package com.sist.jobgem.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "career")
public class Career {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cr_idx", nullable = false)
    private Integer id;

    @Size(max = 30)
    @Column(name = "cr_name", length = 30)
    private String crName;

}