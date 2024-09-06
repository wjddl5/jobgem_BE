package com.sist.jobgem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "education")
@Builder
@AllArgsConstructor
@NoArgsConstructor



public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ed_idx", nullable = false)
    private Integer id;

    @Column(name = "ed_name", length = 30)
    private String edName;
}