package com.sist.jobgem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "education")
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ed_idx", nullable = false)
    private Integer id;

    @Size(max = 30)
    @Column(name = "ed_name", length = 30)
    private String edName;

}